package dev.brunopablo.ecommerce.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(PaginationUserResponse userInfoResponse,
                            BigDecimal totalOrder,
                            List<PaginationItemResponse> itemsOrder) {}