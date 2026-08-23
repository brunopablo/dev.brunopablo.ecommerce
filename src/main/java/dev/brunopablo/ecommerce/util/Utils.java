package dev.brunopablo.ecommerce.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import dev.brunopablo.ecommerce.controller.dto.apiResponse.PaginationInfo;

@Component
public class Utils {

    public PageRequest makePageRequest(Integer pageNumber, 
                                       Integer pageSize, 
                                       String orderBy, 
                                       String fieldOrderBy
    ){

        var orderByDirection = Sort.Direction.DESC;

        if(orderBy.equalsIgnoreCase("asc"))
            orderByDirection = Sort.Direction.ASC;

        return PageRequest.of(pageNumber, pageSize, orderByDirection, fieldOrderBy);
    }

    
    public PaginationInfo makePaginationInfoResponse(int i, int j, long l, int k){

        return null;
    }
}