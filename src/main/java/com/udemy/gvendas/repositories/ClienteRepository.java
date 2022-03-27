package com.udemy.gvendas.repositories;

import com.udemy.gvendas.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByNome(String nome);
}
