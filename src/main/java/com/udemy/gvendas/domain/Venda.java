package com.udemy.gvendas.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo")
    private Cliente cliente;

    public Venda(LocalDate data, Cliente cliente) {
        this.data = data;
        this.cliente = cliente;
    }

    public Venda() {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
