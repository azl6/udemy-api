package com.udemy.gvendas.controllers;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.domain.Produto;
import com.udemy.gvendas.repositories.CategoriaRepository;
import com.udemy.gvendas.repositories.ProdutoRepository;
import com.udemy.gvendas.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Produto> save(@Valid @RequestBody Produto produto){
        return ResponseEntity.ok().body(service.insert(produto));
    }


}
