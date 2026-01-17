package dev.brunopablo.ecommerce.controller.dto;

import java.util.List;

public record ApiResponse<T>(List<T> pageContent,
                            PaginationRequest paginationRequest) {}
