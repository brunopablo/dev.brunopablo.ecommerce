package dev.brunopablo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.brunopablo.ecommerce.entity.ItemPedido;
import dev.brunopablo.ecommerce.entity.ItemPedidoId;

@Repository
public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, ItemPedidoId>{}