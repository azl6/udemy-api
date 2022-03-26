package com.udemy.gvendas.controllers;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.domain.Cliente;
import com.udemy.gvendas.dto.Categoria.CategoriaResponseDTO;
import com.udemy.gvendas.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Cliente")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @ApiOperation(value = "Listar todos os clientes")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> obj =  service.findAll();

        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Encontrar cliente por id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
