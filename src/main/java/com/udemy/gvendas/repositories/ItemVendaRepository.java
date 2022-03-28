package com.udemy.gvendas.repositories;

import com.udemy.gvendas.domain.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    List<ItemVenda> findByVendaCodigo(Long codigoVenda);
}
