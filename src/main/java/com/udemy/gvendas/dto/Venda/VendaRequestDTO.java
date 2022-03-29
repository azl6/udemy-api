package com.udemy.gvendas.dto.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda requisição DTO")
public class VendaRequestDTO {

    @ApiModelProperty(value = "Data")
    private LocalDate data;

    @ApiModelProperty(value = "Itens da venda")
    private List<ItemVendaRequestDTO> itensVendaDTO;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVendaRequestDTO> getItensVendaDTO() {
        return itensVendaDTO;
    }

    public void setItensVendaDTO(List<ItemVendaRequestDTO> itensVendaDTO) {
        this.itensVendaDTO = itensVendaDTO;
    }
}
