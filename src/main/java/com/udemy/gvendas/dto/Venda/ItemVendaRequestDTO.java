package com.udemy.gvendas.dto.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.math.BigDecimal;

@ApiModel("Itens da venda requisição DTO")
public class ItemVendaRequestDTO {

    @ApiModelProperty("Código produto")
    private Long codigoProduto;

    @ApiModelProperty("Quantidade")
    private Integer quantidade;

    @ApiModelProperty("Preço vendido")
    private BigDecimal precoVendido;

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoVendido() {
        return precoVendido;
    }

    public void setPrecoVendido(BigDecimal precoVendido) {
        this.precoVendido = precoVendido;
    }
}
