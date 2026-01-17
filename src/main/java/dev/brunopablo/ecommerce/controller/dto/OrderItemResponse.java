package dev.brunopablo.ecommerce.controller.dto;

import java.math.BigDecimal;

public record OrderItemResponse(OrderItemIdResponse id,
                                BigDecimal total,
                                Integer quantity) {}

                                // List<OrderItemIdResponse> id,