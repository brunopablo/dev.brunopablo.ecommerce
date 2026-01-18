package dev.brunopablo.ecommerce.controller.dto;

import java.math.BigDecimal;

public record PaginationItemResponse(OrderItemIdResponse idItem,
                                     PaginationProductInfoResponse productInfo,
                                     Integer quantity,
                                     BigDecimal totalItem) {}