package com.udemy.gvendas.repositories;

import com.udemy.gvendas.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria findByNome(String nome);
}
