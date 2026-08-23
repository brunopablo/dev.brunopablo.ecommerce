package dev.brunopablo.ecommerce.controller.dto.createOrderRequest;

import java.util.List;

public record CreateOrderRequest(Long idUser,
                                List<CreateOrderItemProduct> orderItems) {}