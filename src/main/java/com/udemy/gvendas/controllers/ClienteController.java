package com.udemy.gvendas.controllers;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.domain.Cliente;
import com.udemy.gvendas.dto.Categoria.CategoriaRequestDTO;
import com.udemy.gvendas.dto.Categoria.CategoriaResponseDTO;
import com.udemy.gvendas.dto.Cliente.ClienteRequestDTO;
import com.udemy.gvendas.dto.Cliente.ClienteResponseDTO;
import com.udemy.gvendas.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<ClienteResponseDTO>> findAll(){
        List<ClienteResponseDTO> obj =  service.findAll().stream().map(
                cliente -> ClienteResponseDTO.converterParaClienteDTO(cliente)
        ).collect(Collectors.toList());
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Encontrar cliente por id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id){
        ClienteResponseDTO obj =  ClienteResponseDTO.converterParaClienteDTO(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Inserir cliente")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ClienteResponseDTO> insert(@Valid @RequestBody ClienteRequestDTO clienteDto){
        Cliente cliente = service.insert(clienteDto.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.converterParaClienteDTO(cliente));
    }

    @ApiOperation(value = "Atualizar cliente")
    @RequestMapping(method = RequestMethod.PUT, value = "/{codigo}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long codigo, @Valid @RequestBody ClienteRequestDTO clienteDto){
        Cliente cliente = service.update(codigo, clienteDto.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.converterParaClienteDTO(cliente));
    }

    @ApiOperation(value = "Deletar cliente")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{codigo}")
    public void delete(@PathVariable Long codigo){
        service.delete(codigo);
    }
}
