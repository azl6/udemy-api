package com.udemy.gvendas.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotBlank(message = "O campo DESCRIÇÃO deve ser preenchido")
    @Length(min = 3, max = 100, message = "O campo DESCRIÇÃO deve ter entre 3 e 100 caracteres")
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "O campo QUANTIDADE deve ser preenchido")
    @Column(name = "quantidade")
    private Integer quantidade;

    @NotNull(message = "O campo PREÇO-CUSTO deve ser preenchido")
    @Column(name = "precoCusto")
    private BigDecimal precoCusto;

    @NotNull(message = "O campo PRECO-VENDA deve ser preenchido")
    @Column(name = "precoVenda")
    private BigDecimal precoVenda;

    @Length(max = 500, message = "O campo OBSERVAÇÃO deve ter, no máximo, 500 caracteres")
    @Column(name = "observacao")
    private String observacao;

    @NotNull(message = "O campo CÓDIGO-CATEGORIA deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
    private Categoria categoria;

    public Produto() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codigo.equals(produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
