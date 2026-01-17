package dev.brunopablo.ecommerce.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.brunopablo.ecommerce.controller.dto.CreateOrderRequest;
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
}