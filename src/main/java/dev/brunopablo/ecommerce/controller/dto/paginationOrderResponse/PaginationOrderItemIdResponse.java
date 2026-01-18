package dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse;

import dev.brunopablo.ecommerce.entity.OrderItemId;


public record PaginationOrderItemIdResponse(Long idOrder,
                                            Long idProduct) {

    public static PaginationOrderItemIdResponse getOrderItemId(OrderItemId itemId)
    {

        return new PaginationOrderItemIdResponse(itemId.getOrder().getId(), 
                                                 itemId.getProduct().getId());
    }                                               
}