package dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import dev.brunopablo.ecommerce.entity.OrderEntity;

public record PaginationOrderResponse(PaginationUserResponse userInfoResponse,
                                      BigDecimal totalOrder,
                                      List<PaginationItemResponse> itemsOrder) {

    public static List<PaginationOrderResponse> getContent(Page<OrderEntity> pageContent){

        return pageContent.stream().map(
            page -> new PaginationOrderResponse(
                PaginationUserResponse.getUserInfo(page.getUser()),
                page.getTotal(),
                PaginationItemResponse.getItemsOrder(page.getItems())
            )
        ).toList();
    }
}