package dev.brunopablo.ecommerce.controller.dto.apiResponse;

public record PaginationRequest(Integer pageNumber,
                                Integer pageSize,
                                Long totalElements,
                                Integer totalPages) {}
