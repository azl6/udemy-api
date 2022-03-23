package com.udemy.gvendas.services;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.exceptions.NotFoundException;
import com.udemy.gvendas.exceptions.SameNameException;
import com.udemy.gvendas.repositories.CategoriaRepository;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public List<Categoria> findAll(){
        return repo.findAll();
    }

    public Categoria findById(Long id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("O código da categoria informada não existe no cadastro"));
    }

    public Categoria save(Categoria categoria){

        if (validateIfNameExists(categoria))
        return repo.save(categoria);

        return null;
    }

    public Categoria update(Long id, Categoria categoria){
        Categoria obj = this.findById(id);
         BeanUtils.copyProperties(categoria, obj, "codigo");
            return repo.save(obj);
    }

    public void delete(Long id){
        this.findById(id);
        repo.deleteById(id);
    }

    public boolean validateIfNameExists(Categoria old_cat){
        Categoria found_cat = repo.findByNome(old_cat.getNome());

        if(found_cat != null && found_cat.getCodigo() != old_cat.getCodigo())
            throw new SameNameException("Nome da categoria já cadastrado");

        return true;

    }
}
