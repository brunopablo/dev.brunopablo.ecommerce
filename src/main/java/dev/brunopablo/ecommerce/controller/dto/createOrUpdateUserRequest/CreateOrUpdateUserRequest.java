package dev.brunopablo.ecommerce.controller.dto.createOrUpdateUserRequest;

public record CreateOrUpdateUserRequest(String name,
                                String address,
                                String number,
                                String complement) {}