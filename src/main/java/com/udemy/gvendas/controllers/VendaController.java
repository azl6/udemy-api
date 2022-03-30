package com.udemy.gvendas.controllers;

import com.udemy.gvendas.dto.Venda.ClienteVendaResponseDTO;
import com.udemy.gvendas.dto.Venda.VendaRequestDTO;
import com.udemy.gvendas.services.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Venda")
@RestController
@RequestMapping(value = "/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @ApiOperation(value = "Listar vendas por cliente", nickname = "listarVendaPorCliente")
    @RequestMapping(method = RequestMethod.GET, value = "/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCliente(@PathVariable Long codigoCliente){
        return ResponseEntity.ok(vendaService.listarVendaPorCliente(codigoCliente));
    }

    @ApiOperation(value = "Listar vendas por código", nickname = "listarVendaPorCódigo")
    @RequestMapping(method = RequestMethod.GET, value = "/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo(@PathVariable Long codigoVenda){
        return ResponseEntity.ok(vendaService.listarVendaPorCodigo(codigoVenda));
    }

    @ApiOperation(value = "Registrar venda", nickname = "salvar")
    @PostMapping("/clientes/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> salvar(@PathVariable Long codigoCliente, @RequestBody VendaRequestDTO vendaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.salvar(codigoCliente, vendaDto));
    }
}
