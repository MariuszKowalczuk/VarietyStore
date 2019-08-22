package pl.mk.variety_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mk.variety_store.dto.OrderItemDto;
import pl.mk.variety_store.model.entity.Order;
import pl.mk.variety_store.model.entity.OrderItem;
import pl.mk.variety_store.model.entity.Product;
import pl.mk.variety_store.repository.OrderItemRepository;
import pl.mk.variety_store.repository.OrderRepository;
import pl.mk.variety_store.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public Order create(Set<OrderItemDto> orderItems) {
        Set<OrderItem> itemsToAdd = new HashSet<>();
        for (OrderItemDto orderItem : orderItems) {
            Product product = productRepository.findById(Long.valueOf(orderItem.getProductId())).orElseThrow(IllegalArgumentException::new);
            OrderItem buildedOrderItem = OrderItem.builder().product(product).quantity(Integer.valueOf(orderItem.getQuantity())).build();
            itemsToAdd.add(buildedOrderItem);
        }
        orderItemRepository.saveAll(itemsToAdd);

        Order order = new Order();
        order.setValue(computeValue(itemsToAdd));
        order.setOrderItems(itemsToAdd);
        orderRepository.save(order);
        return order;

    }

    protected BigDecimal computeValue(Set<OrderItem> orderItems) {
        BigDecimal result = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            Integer quantity = orderItem.getQuantity();
            BigDecimal price = orderItem.getProduct().getPrice();
            for (int i = 0; i < quantity; i++) {
                result = result.add(price);

            }

        }
        return result;
    }


}
