package com.udemy.gvendas.dto.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Itens da venda requisição DTO")
public class ItemVendaRequestDTO {

    @ApiModelProperty("Código produto")
    @NotNull(message = "O campo CÓDIGO-PRODUTO não pode ser nulo")
    private Long codigoProduto;

    @ApiModelProperty("Quantidade")
    @NotNull(message = "O campo QUANTIDADE não pode ser nulo")
    @Min(value = 1, message = "O campo QUANTIDADE deve ser no mínimo 1")
    private Integer quantidade;

    @ApiModelProperty("Preço vendido")
    @NotNull(message = "O campo PREÇO VENDIDO não pode ser nulo")
    @NotNull(message = "O campo DATA não pode ser nulo")
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
