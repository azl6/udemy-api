package com.udemy.gvendas.dto.Cliente;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel("Endereço requisição DTO")
public class EnderecoRequestDTO {

    @ApiModelProperty(value = "logradouro")
    @NotBlank(message = "O campo LOGRADOURO deve ser preenchido")
    @Length(min = 3, max = 30, message = "O campo LOGRADOURO deve ter entre 3 e 30 caracteres")
    private String logradouro;

    @ApiModelProperty(value = "numero")
    @NotNull(message = "O campo NÚMERO não pode ser nulo")
    private Integer numero;

    @ApiModelProperty(value = "complemento")
    @Length(max = 30, message = "O campo LOGRADOURO deve ter no máximo 30 caracteres")
    private String complemento;

    @ApiModelProperty(value = "bairro")
    @Length(min = 3, max = 30, message = "O campo BAIRRO deve ter entre 3 e 30 caracteres")
    private String bairro;

    @ApiModelProperty(value = "cep")
    @NotBlank(message = "O campo CEP deve ser preenchido")
    @Pattern(regexp = "[\\d]{5}-[\\d]{3}", message = "O campo CEP deve ter o seguinte padrão: xxxxx-xxx")
    private String cep;

    @ApiModelProperty(value = "cidade")
    @NotBlank(message = "O campo CIDADE deve ser preenchido")
    @Length(min = 3, max = 30, message = "O campo CIDADE deve ter entre 3 e 30 caracteres")
    private String cidade;

    @ApiModelProperty(value = "estado")
    @NotBlank(message = "O campo ESTADO deve ser preenchido")
    @Length(min = 3, max = 30, message = "O campo ESTADO deve ter entre 3 e 30 caracteres")
    private String estado;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
