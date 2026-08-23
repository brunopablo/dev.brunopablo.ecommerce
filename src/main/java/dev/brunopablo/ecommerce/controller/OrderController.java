package dev.brunopablo.ecommerce.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.brunopablo.ecommerce.controller.dto.apiResponse.ApiResponse;
import dev.brunopablo.ecommerce.controller.dto.createOrderRequest.CreateOrderRequest;
import dev.brunopablo.ecommerce.controller.dto.paginationOrderResponse.PaginationOrderResponse;
import dev.brunopablo.ecommerce.service.OrderService;
import dev.brunopablo.ecommerce.util.Utils;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    private final OrderService orderService;

    private final Utils util;
    
    public OrderController(OrderService orderService, Utils util) {
        this.orderService = orderService;
        this.util = util;
    }


    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest newOrderRequest){
            
        var order = orderService.createOrder(newOrderRequest);
        
        return ResponseEntity.created(URI.create("/order/" + order.getId())).build();
    }
    

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationOrderResponse>> listOrders(
        @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
        @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
        @RequestParam(name="orderBy", defaultValue="desc") String orderBy,
        @RequestParam(name="userName", required=false) String userName
    ){
        
        var pages = orderService.listOrders(pageNumber, pageSize, orderBy, userName);
        
        var apiResponse = new ApiResponse<>(
            PaginationOrderResponse.getContent(pages),
            util.makePaginationInfoResponse(pages.getNumber(),
                                            pages.getSize(),
                                            pages.getTotalElements(),
                                            pages.getTotalPages())
        );   
        
        return ResponseEntity.ok(apiResponse);
    }
}