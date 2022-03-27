package com.udemy.gvendas.dto.Cliente;

import com.udemy.gvendas.domain.Cliente;
import com.udemy.gvendas.domain.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel("Cliente requisição DTO")
public class ClienteRequestDTO {

    @ApiModelProperty(value = "nome")
    @NotBlank(message = "O campo NOME deve ser preenchido")
    @Length(min = 3, max = 50, message = "O campo NOME deve ter entre 3 e 50 caracteres")
    private String nome;


    @ApiModelProperty(value = "telefone")
    @NotBlank(message = "O campo TELEFONE deve ser preenchido")
    @Pattern(regexp = "\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}", message = "O campo TELEFONE deve seguir o seguinte padrão: (xx)xxxxx-xxxx")
    private String telefone;

    @ApiModelProperty(value = "ativo")
    @NotNull(message = "O campo ATIVO não pode ser nulo")
    private Boolean ativo;

    @ApiModelProperty(value = "endereco")
    @NotNull
    @Valid
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
