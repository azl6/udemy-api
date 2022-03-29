package com.udemy.gvendas.services;

import com.udemy.gvendas.domain.Produto;
import com.udemy.gvendas.exceptions.CategoriaNullException;
import com.udemy.gvendas.exceptions.NotFoundException;
import com.udemy.gvendas.exceptions.ProdutoDuplicadoException;
import com.udemy.gvendas.repositories.CategoriaRepository;
import com.udemy.gvendas.repositories.ProdutoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(Long id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("O código do produto informado não existe no cadastro"));
    }

    public Produto insert(Produto produto){
        validarCodigoCategoriaDoProdutoExiste(produto.getCategoria().getCodigo());
        validarProdutoDuplicado(produto);
        return produtoRepository.save(produto);
    }

    public Produto update(Long id, Produto produtoRecebido){
        validarCodigoCategoriaDoProdutoExiste(produtoRecebido.getCategoria().getCodigo());
        validarProdutoDuplicado(produtoRecebido);
        Produto produto = this.findById(id);
        BeanUtils.copyProperties(produtoRecebido, produto = new Produto());
        return produtoRepository.save(produto);
    }

    public void validarProdutoDuplicado(Produto produto){
        if(produtoRepository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao()).isPresent()){
            throw new ProdutoDuplicadoException(String.format("O produto %s já está cadastrado", produto.getDescricao()));
        }
    }
    public void validarCodigoCategoriaDoProdutoExiste(Long codigoCategoria){
        if (codigoCategoria == null)
            throw new CategoriaNullException("A categoria não pode ser nula");
        categoriaService.findById(codigoCategoria);
    }

    protected Produto validarProdutoExiste(Long codigoProduto){
        Optional<Produto> produto = produtoRepository.findById(codigoProduto);
    }

    public void delete(Long id){
        this.findById(id);
        produtoRepository.deleteById(id);
    }

}
