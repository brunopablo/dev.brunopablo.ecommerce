package dev.brunopablo.ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.brunopablo.ecommerce.controller.dto.CreateOrderRequest;
import dev.brunopablo.ecommerce.entity.OrderEntity;
import dev.brunopablo.ecommerce.entity.OrderItemEntity;
import dev.brunopablo.ecommerce.entity.OrderItemId;
import dev.brunopablo.ecommerce.entity.ProductEntity;
import dev.brunopablo.ecommerce.repository.OrderItemRepository;
import dev.brunopablo.ecommerce.repository.OrderRepository;
import dev.brunopablo.ecommerce.repository.ProductRepository;
import dev.brunopablo.ecommerce.repository.UserRepository;
import dev.brunopablo.ecommerce.util.Util;

@Service
public class OrderService {

    private final Util util;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, 
                        OrderItemRepository orderItemRepository,
                        ProductRepository productRepository, 
                        UserRepository userRepository,
                        Util util) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.util = util;
    }

    public OrderEntity createOrder(CreateOrderRequest newOrder){
        
        var order = new OrderEntity();

        var listOrderItems = getListOrdemItems(newOrder, order);

        var userEntity = userRepository.findById(newOrder.idUser()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        );

        var orderTotal = getOrderTotal(listOrderItems);

        order.setUser(userEntity);

        order.setDateOrder(LocalDateTime.now());

        order.setItems(listOrderItems);

        order.setTotal(orderTotal);

        var orderEntity = orderRepository.save(order);
    
        return orderEntity;
    }

    private BigDecimal getOrderTotal(List<OrderItemEntity> listOrderItems) {
        
        return listOrderItems.stream()
            .map(OrderItemEntity::getTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderItemEntity> getListOrdemItems(CreateOrderRequest newOrder, OrderEntity order){
        
        return newOrder.orderItems().stream().map(
            item -> getOrderItem(item.idProduct(), item.quantity(), order)                
        ).toList();
    }

    private OrderItemEntity getOrderItem(Long id_product, 
                                         Integer quantity, 
                                         OrderEntity order){

        var product = getProduct(id_product);

        var orderItemId = new OrderItemId(
            order,
            product
        );

        var orderItem = new OrderItemEntity();
        
        orderItem.setId(orderItemId);

        orderItem.setTotal(product.getPrice().multiply(BigDecimal.valueOf(quantity)));

        orderItem.setQuantity(quantity);

        return orderItem;
    }

    private ProductEntity getProduct(Long id_product) {
        return productRepository.findById(id_product).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado")
        );
    }

    public Page<OrderEntity> listOrders(Integer pageNumber, 
                                        Integer pageSize, 
                                        String orderBy, 
                                        String userName) {

        var pageRequest = util.makePageRequest(pageNumber, 
                                               pageSize, 
                                               orderBy,
                                               "dateOrder");

        var pages = getPages(pageRequest, userName);

        return pages;
    }

    private Page<OrderEntity> getPages(PageRequest pageRequest, String userName) {

        if(userName != null){
            return orderRepository.findByUser_Name(userName, pageRequest);
        }

        return orderRepository.findAll(pageRequest);
    }
}