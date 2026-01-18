package dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse;

import java.math.BigDecimal;
import java.util.List;

public record PaginationOrderResponse(PaginationUserResponse userInfoResponse,
                            BigDecimal totalOrder,
                            List<PaginationItemResponse> itemsOrder) {}