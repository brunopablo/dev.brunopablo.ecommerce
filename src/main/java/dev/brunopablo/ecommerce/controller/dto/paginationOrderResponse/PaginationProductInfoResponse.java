package dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse;

import java.math.BigDecimal;
import java.util.List;

public record PaginationProductInfoResponse(String nameProduct,
                                            BigDecimal priceProduct,
                                            List<PaginationTagInfoResponse> tagProduct) {}