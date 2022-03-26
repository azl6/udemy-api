package com.udemy.gvendas.dto.Cliente;

import com.udemy.gvendas.domain.Cliente;
import com.udemy.gvendas.domain.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@ApiModel("Cliente retorno DTO")
public class ClienteResponseDTO {

    @ApiModelProperty(value = "codigo")
    private Long codigo;

    @ApiModelProperty(value = "nome")
    private String nome;

    @ApiModelProperty(value = "telefone")
    private String telefone;

    @ApiModelProperty(value = "ativo")
    private Boolean ativo;

    private EnderecoResponseDTO enderecoDto;

    public ClienteResponseDTO(Long codigo, String nome, String telefone, Boolean ativo, EnderecoResponseDTO enderecoDto) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
        this.enderecoDto = enderecoDto;
    }

    public static ClienteResponseDTO converterParaClienteDTO(Cliente cliente){
        EnderecoResponseDTO enderecoDto = new EnderecoResponseDTO(
                cliente.getEndereço().getLogradouro(),
                cliente.getEndereço().getNumero(),
                cliente.getEndereço().getComplemento(),
                cliente.getEndereço().getBairro(),
                cliente.getEndereço().getCep(),
                cliente.getEndereço().getCidade(),
                cliente.getEndereço().getEstado()
        );
        return new ClienteResponseDTO(cliente.getCodigo(), cliente.getNome(), cliente.getTelefone(), cliente.getAtivo(), enderecoDto);
    }

    @Override
    public String toString() {
        return "ClienteResponseDTO{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", ativo=" + ativo +
                ", enderecoDto=" + enderecoDto +
                '}';
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public EnderecoResponseDTO getEnderecoDto() {
        return enderecoDto;
    }

    public void setEnderecoDto(EnderecoResponseDTO enderecoDto) {
        this.enderecoDto = enderecoDto;
    }
}
