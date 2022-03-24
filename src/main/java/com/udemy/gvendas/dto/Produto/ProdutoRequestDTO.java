package com.udemy.gvendas.dto.Produto;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.domain.Produto;
import com.udemy.gvendas.dto.Categoria.CategoriaResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Produto requisição DTO")
public class ProdutoRequestDTO {

    @ApiModelProperty(value = "Descrição")
    @NotBlank(message = "O campo DESCRIÇÃO deve ser preenchido")
    @Length(min = 3, max = 100, message = "O campo DESCRIÇÃO deve ter entre 3 e 100 caracteres")
    private String descricao;

    @ApiModelProperty(value = "Quantidade")
    @NotNull(message = "O campo QUANTIDADE deve ser preenchido")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço Custo")
    @NotNull(message = "O campo PREÇO-CUSTO deve ser preenchido")
    private BigDecimal precoCusto;

    @ApiModelProperty(value = "Preço Venda")
    @NotNull(message = "O campo PRECO-VENDA deve ser preenchido")
    private BigDecimal precoVenda;

    @ApiModelProperty(value = "Observação")
    @Length(max = 500, message = "O campo OBSERVAÇÃO deve ter, no máximo, 500 caracteres")
    private String observacao;

    @ApiModelProperty(value = "Categoria")
    @NotNull(message = "O campo CÓDIGO-CATEGORIA deve ser preenchido")
    private Categoria categoria;

    public Produto converterParaEntidade(){
        return new Produto(this.descricao, this.quantidade, this.precoCusto, this.precoVenda, this.observacao, this.categoria);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
