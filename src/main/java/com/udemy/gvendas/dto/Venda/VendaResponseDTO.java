package com.udemy.gvendas.dto.Venda;

import com.udemy.gvendas.domain.ItemVenda;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda retorno DTO")
public class VendaResponseDTO {

    @ApiModelProperty(value = "Código")
    private Long codigo;

    @ApiModelProperty(value = "Data")
    private LocalDate data;

    @ApiModelProperty(value = "Itens da venda")
    private List<ItemVendaResponseDTO> itemVendaDTO;

    public VendaResponseDTO(Long codigo, LocalDate data, List<ItemVendaResponseDTO> itemVendaDTO) {
        this.codigo = codigo;
        this.data = data;
        this.itemVendaDTO = itemVendaDTO;
    }

    public VendaResponseDTO() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVenda> getItemVendaDTO() {
        return itemVendaDTO;
    }

    public void setItemVendaDTO(List<ItemVenda> itemVendaDTO) {
        this.itemVendaDTO = itemVendaDTO;
    }
}
