package com.udemy.gvendas.dto.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda requisição DTO")
public class VendaRequestDTO {

    @ApiModelProperty(value = "Data")
    @NotNull(message = "O campo DATA não pode ser nulo")
    private LocalDate data;

    @Valid
    @NotNull(message = "O campo ITENS DA VENDA não pode ser nulo")
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
