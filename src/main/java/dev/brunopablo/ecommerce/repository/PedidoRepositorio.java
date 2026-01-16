package dev.brunopablo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.brunopablo.ecommerce.entity.PedidoEntidade;

@Repository
public interface PedidoRepositorio extends JpaRepository<PedidoEntidade, Long>{}