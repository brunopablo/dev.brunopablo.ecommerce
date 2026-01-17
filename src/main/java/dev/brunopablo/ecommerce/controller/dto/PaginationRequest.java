package dev.brunopablo.ecommerce.controller.dto;

public record PaginationRequest(Integer pageNumber,
                                Integer pageSize,
                                Long totalElements,
                                Integer totalPages) {}
