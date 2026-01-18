package dev.brunopablo.ecommerce.controller.dto;

import java.math.BigDecimal;

public record PaginationProductInfoResponse(String nameProduct,
                                            BigDecimal priceProduct) {}