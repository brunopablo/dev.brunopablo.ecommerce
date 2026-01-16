package dev.brunopablo.ecommerce.controller.dto;

public record CreateUserRequest(String name,
                                String address,
                                String number,
                                String complement) {}