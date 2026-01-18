package dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse;

import dev.brunopablo.ecommerce.entity.UserEntity;

public record PaginationUserResponse(Long idUser,
                                     String nameUser) {

    public static PaginationUserResponse getUserInfo(UserEntity userEntity) {

        return new PaginationUserResponse(userEntity.getId(), 
                                          userEntity.getName());
    }
}