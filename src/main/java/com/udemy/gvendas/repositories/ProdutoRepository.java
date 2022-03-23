package com.udemy.gvendas.repositories;

import com.udemy.gvendas.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao);
}
