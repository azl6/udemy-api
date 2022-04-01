package com.udemy.gvendas.services;

import com.udemy.gvendas.domain.Cliente;
import com.udemy.gvendas.domain.ItemVenda;
import com.udemy.gvendas.domain.Produto;
import com.udemy.gvendas.domain.Venda;
import com.udemy.gvendas.dto.Venda.*;
import com.udemy.gvendas.exceptions.InvalidQuantityException;
import com.udemy.gvendas.exceptions.NotFoundException;
import com.udemy.gvendas.repositories.ItemVendaRepository;
import com.udemy.gvendas.repositories.VendaRepository;
import io.jaegertracing.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {


    private ClienteService clienteService;
    private VendaRepository vendaRepository;
    private ItemVendaRepository itemVendaRepository;
    private ProdutoService produtoService;

    @Autowired
    public VendaService(ClienteService clienteService, VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository, ProdutoService produtoService) {
        this.clienteService = clienteService;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.produtoService = produtoService;
    }

    public ClienteVendaResponseDTO listarVendaPorCliente(Long codigoCliente){
        Cliente clienteBuscado = clienteService.findById(codigoCliente);
        List<VendaResponseDTO> vendaResponseDTOList = vendaRepository.findByClienteCodigo(codigoCliente).stream()
                .map(venda -> criandoVendaResponseDTO(venda, itemVendaRepository.findByVendaPorCodigo(venda.getCodigo()))).collect(Collectors.toList());

        return new ClienteVendaResponseDTO(clienteBuscado.getNome(), vendaResponseDTOList);
    }


    private VendaResponseDTO criandoVendaResponseDTO(Venda venda, List<ItemVenda> itensVendaList){
            List<ItemVendaResponseDTO> itensVendaResponseDto = itensVendaList
                    .stream().map(this::criandoItensVendaResponseDto).collect(Collectors.toList());

            return new VendaResponseDTO(venda.getCodigo(), venda.getData(), itensVendaResponseDto);
    }

    private ItemVendaResponseDTO criandoItensVendaResponseDto(ItemVenda itemVenda){
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(), itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
    }

    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigo){
        Venda vendaExiste = validarVendaExiste(codigo);
        List<ItemVenda> itensVendaList = itemVendaRepository.findByVendaPorCodigo(vendaExiste.getCodigo());
        return retornandoClienteVendaResponseDTO(vendaExiste, itensVendaList);
    }

    private Venda validarVendaExiste(Long codigo){
        Optional<Venda> venda = vendaRepository.findById(codigo);
        return venda.orElseThrow(() -> new NotFoundException("O código da venda informado não está cadastrado"));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public ClienteVendaResponseDTO salvar(Long codigoCliente, VendaRequestDTO vendaDto){
        Cliente cliente = clienteService.findById(codigoCliente);
        validarProdutoExisteEAtualizarQuantidade(vendaDto.getItensVendaDTO());
        Venda vendaSalva = salvarVenda(cliente, vendaDto);

        return retornandoClienteVendaResponseDTO(vendaSalva, itemVendaRepository.findByVendaPorCodigo(vendaSalva.getCodigo()));
    }

    private ClienteVendaResponseDTO retornandoClienteVendaResponseDTO(Venda venda, List<ItemVenda> itensVendaList){
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(), Arrays.asList(criandoVendaResponseDTO(venda,
                itensVendaList)));
    }

    private Venda salvarVenda(Cliente cliente, VendaRequestDTO vendaDto){
        Venda vendaSalva = vendaRepository.save(new Venda(vendaDto.getData(), cliente));
        vendaDto.getItensVendaDTO().stream().map(x -> criandoItemVenda(x, vendaSalva))
                .forEach(itemVendaRepository::save);

        return vendaSalva;
    }

    private void validarProdutoExisteEAtualizarQuantidade(List<ItemVendaRequestDTO> itensVendaDto){
        itensVendaDto.forEach(x -> {
            System.out.println(String.format("Produto %d: qtd. requerida - %d", x.getCodigoProduto(), x.getQuantidade()));
            Produto produto = produtoService.findById(x.getCodigoProduto());
            System.out.println(String.format("Produto %d: qtd. disponível - %d", produto.getCodigo(), produto.getQuantidade()));
            validarQuantidadeProdutoExiste(produto, x.getQuantidade());
            produto.setQuantidade(produto.getQuantidade() - x.getQuantidade());
            produtoService.atualizarQuantidadeAposVenda(produto);
        });
    }

    private void validarQuantidadeProdutoExiste(Produto produto, Integer qtdVendaDto){

        if(produto.getQuantidade() < qtdVendaDto)
            throw new InvalidQuantityException(String.format("A quantidade do produto %s informada excede o estoque [ESTOQUE ATUAL: %d unidades]",
                    produto.getDescricao(), produto.getQuantidade()));


    }

    private ItemVenda criandoItemVenda(ItemVendaRequestDTO itemVendaDto, Venda venda){
        return new ItemVenda(itemVendaDto.getQuantidade(), itemVendaDto.getPrecoVendido(), new Produto(itemVendaDto.getCodigoProduto()), venda);
    }
}
