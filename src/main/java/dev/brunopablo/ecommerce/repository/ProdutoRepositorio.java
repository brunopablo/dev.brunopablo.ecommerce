package dev.brunopablo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.brunopablo.ecommerce.entity.ProdutoEntidade;

@Repository
public interface ProdutoRepositorio extends JpaRepository<ProdutoEntidade, Long>{}