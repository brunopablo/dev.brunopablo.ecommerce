package dev.brunopablo.ecommerce.controller.dto.apiResponse;

public record PaginationInfo(Integer pageNumber,
                                Integer pageSize,
                                Long totalElements,
                                Integer totalPages) {}
