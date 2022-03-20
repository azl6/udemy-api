package com.udemy.gvendas.services;

import com.udemy.gvendas.domain.Categoria;
import com.udemy.gvendas.repositories.CategoriaRepository;
import org.hibernate.ObjectNotFoundException;
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
        return obj.orElse(null);
    }

    public Categoria save(Categoria categoria){
        return repo.save(categoria);
    }

    public Categoria update(Long id, Categoria categoria){
        Categoria obj = this.findById(id);
        if(obj == null) throw new ObjectNotFoundException(1, "Não encontrado");
         BeanUtils.copyProperties(categoria, obj, "codigo");
            return repo.save(obj);
    }
}
