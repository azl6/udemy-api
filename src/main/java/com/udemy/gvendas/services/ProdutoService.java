package com.udemy.gvendas.services;

import com.udemy.gvendas.domain.Produto;
import com.udemy.gvendas.repositories.ProdutoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public List<Produto> findAll(){
        return repo.findAll();
    }

    public Produto findById(Long id){
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(1, "Objeto não encontrado"));
    }

    public Produto insert(Produto produto){
        return repo.save(produto);
    }
}
