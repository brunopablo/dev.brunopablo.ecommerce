package dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse;

import java.util.List;

import dev.brunopablo.ecommerce.entity.TagEntity;

public record PaginationTagInfoResponse(Long idTag,
                                        String nameTag) {

    public static List<PaginationTagInfoResponse> getTags(List<TagEntity> tags) {
        
        return tags.stream().map(
            tag -> new PaginationTagInfoResponse(tag.getId(), 
                                                 tag.getName())
        ).toList();
    }
}