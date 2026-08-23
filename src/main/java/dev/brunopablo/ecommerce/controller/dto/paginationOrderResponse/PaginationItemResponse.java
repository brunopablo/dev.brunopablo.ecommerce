package dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse;

import java.math.BigDecimal;
import java.util.List;

import dev.brunopablo.ecommerce.entity.OrderItemEntity;

public record PaginationItemResponse(PaginationOrderItemIdResponse idItem,
                                     PaginationProductInfoResponse productInfo,
                                     Integer quantity,
                                     BigDecimal totalItem) {

    public static List<PaginationItemResponse> getItemsOrder(List<OrderItemEntity> items) {

        return items.stream().map(
            item -> new PaginationItemResponse(
                PaginationOrderItemIdResponse.getOrderItemId(item.getId()), 
                PaginationProductInfoResponse.getProductInfo(item.getId().getProduct()), 
                item.getQuantity(), 
                item.getTotal()
            )
        ).toList();
    }
}