/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dev.brunopablo.ecommerce.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(Long idOrder,
                            BigDecimal totalOrder,
                            LocalDateTime dateOrder,
                            Long orderUserId,
                            List<OrderItemResponse> items) {}