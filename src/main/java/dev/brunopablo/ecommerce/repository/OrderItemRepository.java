package dev.brunopablo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.brunopablo.ecommerce.entity.OrderItemEntity;
import dev.brunopablo.ecommerce.entity.OrderItemId;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemId>{}