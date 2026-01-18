package dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse;

import java.math.BigDecimal;
import java.util.List;

import dev.brunopablo.ecommerce.entity.ProductEntity;

public record PaginationProductInfoResponse(String nameProduct,
                                            BigDecimal priceProduct,
                                            List<PaginationTagInfoResponse> tagProduct) {

    public static PaginationProductInfoResponse getProductInfo(ProductEntity product) {

        return new PaginationProductInfoResponse(
            product.getName(), 
            product.getPrice(), 
            PaginationTagInfoResponse.getTags(product.getTags()));
    }
}