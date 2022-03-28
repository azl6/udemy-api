package com.udemy.gvendas.services;

import com.udemy.gvendas.domain.Cliente;
import com.udemy.gvendas.domain.ItemVenda;
import com.udemy.gvendas.domain.Venda;
import com.udemy.gvendas.dto.Venda.ClienteVendaResponseDTO;
import com.udemy.gvendas.dto.Venda.ItemVendaResponseDTO;
import com.udemy.gvendas.dto.Venda.VendaResponseDTO;
import com.udemy.gvendas.exceptions.NotFoundException;
import com.udemy.gvendas.repositories.ItemVendaRepository;
import com.udemy.gvendas.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {


    private ClienteService clienteService;
    private VendaRepository vendaRepository;
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    public VendaService(ClienteService clienteService, VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository) {
        this.clienteService = clienteService;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }

    public ClienteVendaResponseDTO listarVendaPorCliente(Long codigoCliente){
        Cliente clienteBuscado = clienteService.findById(codigoCliente);
        List<VendaResponseDTO> vendaResponseDTOList = vendaRepository.findByClienteCodigo(codigoCliente).stream()
                .map(this::criandoVendaResponseDTO).collect(Collectors.toList());

        return new ClienteVendaResponseDTO(clienteBuscado.getNome(), vendaResponseDTOList);
    }

    private VendaResponseDTO criandoVendaResponseDTO(Venda venda){
            List<ItemVendaResponseDTO> itensVendaResponseDto = itemVendaRepository.findByVendaCodigo(venda.getCodigo())
                    .stream().map(this::criandoItensVendaResponseDto).collect(Collectors.toList());

            return new VendaResponseDTO(venda.getCodigo(), venda.getData(), itensVendaResponseDto);
    }

    private ItemVendaResponseDTO criandoItensVendaResponseDto(ItemVenda itemVenda){
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(), itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
    }

    public ClienteVendaResponseDTO findById(Long codigo){
        Venda vendaExiste = validarVendaExiste(codigo);
        return new ClienteVendaResponseDTO(vendaExiste.getCliente().getNome(), Arrays.asList(criandoVendaResponseDTO(vendaExiste)));
    }

    private Venda validarVendaExiste(Long codigo){
        Optional<Venda> venda = vendaRepository.findById(codigo);
        return venda.orElseThrow(() -> new NotFoundException("O código da venda informado não está cadastrado"));
    }
}
