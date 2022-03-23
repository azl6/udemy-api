package com.udemy.gvendas.controllers;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.services.CategoriaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @ApiOperation(value = "Listar todas as categorias")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> obj =  service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Encontrar categoria por id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        Categoria obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Inserir categoria")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Categoria categoria){
        service.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Atualizar categoria")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @RequestBody Categoria categoria){
        service.update(id, categoria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Deletar categoria")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
