package dev.brunopablo.ecommerce.controller.dto;

import java.util.List;

public record CreateOrderRequest(Long idUser,
                                List<OrderItemProduct> orderItems) {}