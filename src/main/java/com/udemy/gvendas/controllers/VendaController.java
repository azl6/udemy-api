package com.udemy.gvendas.controllers;

import com.udemy.gvendas.dto.Venda.ClienteVendaResponseDTO;
import com.udemy.gvendas.services.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
