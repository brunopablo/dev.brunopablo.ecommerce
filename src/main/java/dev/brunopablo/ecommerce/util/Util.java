package dev.brunopablo.ecommerce.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class Util {

    public PageRequest makePageRequest(Integer pageNumber, 
                                       Integer pageSize, 
                                       String orderBy, 
                                       String fieldOrderBy){

        var orderByDirection = Sort.Direction.DESC;

        if(orderBy.equalsIgnoreCase("asc"))
            orderByDirection = Sort.Direction.ASC;

        return PageRequest.of(pageNumber, pageSize, orderByDirection, fieldOrderBy);
    }
}