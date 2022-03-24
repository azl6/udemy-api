package com.udemy.gvendas.controllers;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.dto.Categoria.CategoriaRequestDTO;
import com.udemy.gvendas.dto.Categoria.CategoriaResponseDTO;
import com.udemy.gvendas.services.CategoriaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @ApiOperation(value = "Listar todas as categorias")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaResponseDTO>> findAll(){
        List<CategoriaResponseDTO> obj =  service.findAll().stream()
                .map(categoria -> CategoriaResponseDTO.converterParaCategoriaDTO(categoria))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Encontrar categoria por id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Long id){
        Categoria obj =  service.findById(id);
        return ResponseEntity.ok().body(CategoriaResponseDTO.converterParaCategoriaDTO(obj));
    }

    @ApiOperation(value = "Inserir categoria")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoriaResponseDTO> insert(@Valid @RequestBody CategoriaRequestDTO categoria){
        Categoria cat = service.save(categoria.converterParaEntidade(categoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.converterParaCategoriaDTO(cat));
    }

    @ApiOperation(value = "Atualizar categoria")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(@Valid @PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaDto){
        Categoria cat = service.update(id, categoriaDto.converterParaEntidade(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.converterParaCategoriaDTO(cat));
    }

    @ApiOperation(value = "Deletar categoria")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
