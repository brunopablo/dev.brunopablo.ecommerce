package dev.brunopablo.ecommerce.controller.dto;

public record CreateOrUpdateUserRequest(String name,
                                String address,
                                String number,
                                String complement) {}