package pl.mk.variety_store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mk.variety_store.dto.OrderItemDto;
import pl.mk.variety_store.model.entity.Order;
import pl.mk.variety_store.service.OrderService;

import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("orders")
    public Order createOrder(@RequestBody Set<OrderItemDto> orderItems) {
        return orderService.create(orderItems);
    }
}
