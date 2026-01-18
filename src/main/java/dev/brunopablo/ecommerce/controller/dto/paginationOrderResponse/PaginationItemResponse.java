package dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse;

import java.math.BigDecimal;

public record PaginationItemResponse(PaginationOrderItemIdResponse idItem,
                                     PaginationProductInfoResponse productInfo,
                                     Integer quantity,
                                     BigDecimal totalItem) {}