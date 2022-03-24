package com.udemy.gvendas.controllers;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.domain.Produto;
import com.udemy.gvendas.dto.Produto.ProdutoRequestDTO;
import com.udemy.gvendas.dto.Produto.ProdutoResponseDTO;
import com.udemy.gvendas.repositories.CategoriaRepository;
import com.udemy.gvendas.repositories.ProdutoRepository;
import com.udemy.gvendas.services.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @ApiOperation(value = "Listar todos os produtos")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
        List<ProdutoResponseDTO> listaDto = service.findAll().stream().map(produto -> ProdutoResponseDTO.converterParaProdutoDTO(produto))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDto);
    }

    @ApiOperation(value = "Encontar produto por id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(value = "Inserir produto")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProdutoResponseDTO> save(@Valid @RequestBody ProdutoRequestDTO produtoDto){
        return ResponseEntity.ok().body(ProdutoResponseDTO.converterParaProdutoDTO(service.insert(produtoDto.converterParaEntidade())));
    }

    @ApiOperation(value = "Atualizar produto")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO produtoDto){
        Produto produto = service.update(id, produtoDto.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converterParaProdutoDTO(produto));
    }

    @ApiOperation(value = "Deletar produto")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }


}
