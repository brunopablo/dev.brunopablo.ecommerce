package dev.brunopablo.ecommerce.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.brunopablo.ecommerce.controller.dto.ApiResponse;
import dev.brunopablo.ecommerce.controller.dto.CreateOrderRequest;
import dev.brunopablo.ecommerce.controller.dto.OrderItemIdResponse;
import dev.brunopablo.ecommerce.controller.dto.OrderResponse;
import dev.brunopablo.ecommerce.controller.dto.PaginationItemResponse;
import dev.brunopablo.ecommerce.controller.dto.PaginationProductInfoResponse;
import dev.brunopablo.ecommerce.controller.dto.PaginationRequest;
import dev.brunopablo.ecommerce.controller.dto.PaginationUserResponse;
import dev.brunopablo.ecommerce.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest newOrderRequest){
            
        var order = orderService.createOrder(newOrderRequest);
        
        return ResponseEntity.created(URI.create("/order/" + order.getId())).build();
    }
    

    @GetMapping
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
        @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
        @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
        @RequestParam(name="orderBy", defaultValue="desc") String orderBy,
        @RequestParam(name="userName", required=false) String userName
    ){
        
        var pages = orderService.listOrders(pageNumber, pageSize, orderBy, userName);
        
        var orderResponse = pages.getContent().stream().map(
            page -> new OrderResponse(
                new PaginationUserResponse(
                    page.getUser().getId(),
                    page.getUser().getName()
                ),
                page.getTotal(),
                page.getItems().stream().map(
                    item -> new PaginationItemResponse(
                        new OrderItemIdResponse(
                            item.getId().getOrder().getId(),
                            item.getId().getProduct().getId()
                        ),
                        new PaginationProductInfoResponse(
                            item.getId().getProduct().getName(),
                            item.getId().getProduct().getPrice()
                        ),
                        item.getQuantity(),
                        item.getTotal()
                    )
                ).toList()
            )
        ).toList();
        
        
        var apiResponse = new ApiResponse<>(
            orderResponse,
            new PaginationRequest(
                pages.getNumber(),
                pages.getSize(),
                pages.getTotalElements(),
                pages.getTotalPages()
            )
        );   
        
        return ResponseEntity.ok(apiResponse);
    }
}