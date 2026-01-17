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
import dev.brunopablo.ecommerce.controller.dto.OrderItemResponse;
import dev.brunopablo.ecommerce.controller.dto.OrderResponse;
import dev.brunopablo.ecommerce.controller.dto.PaginationRequest;
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

        IO.println("ID USUARIO = " + newOrderRequest.idUser());

        var order = orderService.createOrder(newOrderRequest);

        return ResponseEntity.created(URI.create("/order/" + order.getId())).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
        @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
        @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
        @RequestParam(name="orderBy", defaultValue="desc") String orderBy
    ){

        var pages = orderService.listOrders(pageNumber, pageSize, orderBy);

        var apiResponse = new ApiResponse<>(
            pages.getContent().stream().map(
                page -> new OrderResponse(
                    page.getId(),
                    page.getTotal(),
                    page.getDateOrder(),
                    page.getUser().getId(),
                    page.getItems().stream().map(
                        orderItem -> new OrderItemResponse(
                            new OrderItemIdResponse(
                                orderItem.getId().getOrder().getId(),
                                orderItem.getId().getProduct().getId()
                            ),
                            orderItem.getTotal(),
                            orderItem.getQuantity()
                        )
                    ).toList()
                )
            ).toList(),
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