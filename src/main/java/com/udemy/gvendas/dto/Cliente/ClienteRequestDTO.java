package com.udemy.gvendas.dto.Cliente;

import com.udemy.gvendas.domain.Cliente;
import com.udemy.gvendas.domain.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Cliente requisição DTO")
public class ClienteRequestDTO {

    @ApiModelProperty(value = "nome")
    private String nome;

    @ApiModelProperty(value = "telefone")
    private String telefone;

    @ApiModelProperty(value = "ativo")
    private Boolean ativo;

    @ApiModelProperty(value = "endereco")
    private EnderecoRequestDTO enderecoDto;

    public Cliente converterParaEntidade(){
        Endereco endereco = new Endereco(
                enderecoDto.getLogradouro(),
                enderecoDto.getNumero(),
                enderecoDto.getComplemento(),
                enderecoDto.getBairro(),
                enderecoDto.getCep(),
                enderecoDto.getCidade(),
                enderecoDto.getEstado());

        return new Cliente(getNome(), getTelefone(), getAtivo(), endereco);
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

    public EnderecoRequestDTO getEnderecoDto() {
        return enderecoDto;
    }

    public void setEnderecoDto(EnderecoRequestDTO enderecoDto) {
        this.enderecoDto = enderecoDto;
    }
}
