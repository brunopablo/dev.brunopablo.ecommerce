package dev.brunopablo.ecommerce.controller.dto.apiResponse;

import java.util.List;

public record ApiResponse<T>(List<T> pageContent,
                            PaginationRequest paginationRequest) {}
