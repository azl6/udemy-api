package com.udemy.gvendas.dto.Categoria;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.domain.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel("Categoria requisição DTO")
public class CategoriaRequestDTO implements Serializable {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "O campo NOME deve ser preenchido")
    @Length(min = 3, max = 50, message = "O campo NOME deve ter entre 3 e 50 caracteres")
    private String nome;


    public CategoriaRequestDTO(String nome) {
        this.nome = nome;
    }

    public CategoriaRequestDTO() {
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria converterParaEntidade(CategoriaRequestDTO categoria){
        return new Categoria(categoria.getNome());
    }

    public Categoria converterParaEntidade(Long codigo){
        return new Categoria(codigo, nome);
    }

}
