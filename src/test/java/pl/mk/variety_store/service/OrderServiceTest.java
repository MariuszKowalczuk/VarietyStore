package pl.mk.variety_store.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mk.variety_store.dto.OrderItemDto;
import pl.mk.variety_store.model.entity.Order;
import pl.mk.variety_store.model.entity.OrderItem;
import pl.mk.variety_store.model.entity.Product;
import pl.mk.variety_store.repository.OrderItemRepository;
import pl.mk.variety_store.repository.OrderRepository;
import pl.mk.variety_store.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Mariusz Kowalczuk
 */
public class OrderServiceTest {
    private OrderService orderService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(orderRepository, orderItemRepository, productRepository);
    }

    @Test
    public void createOrderShouldCreateNewOrder() {
        Set<OrderItem> orderItems = new HashSet<>();
        Product product = Product.builder().id(1L).price(BigDecimal.valueOf(5.50)).build();
        OrderItem orderItem = OrderItem.builder().product(product).quantity(1).build();
        Product product2 = Product.builder().id(2L).price(BigDecimal.valueOf(10.50)).build();
        OrderItem orderItem2 = OrderItem.builder().product(product2).quantity(2).build();
        orderItems.add(orderItem);
        orderItems.add(orderItem2);
        Order order = Order.builder().orderItems(orderItems).build();
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        Set<OrderItemDto> orderItemDtos = new HashSet<>();
        OrderItemDto orderItemDto = OrderItemDto.builder().productId(orderItem.getProduct().getId().toString()).quantity(orderItem.getQuantity().toString()).build();
        OrderItemDto orderItemDto2 = OrderItemDto.builder().productId(orderItem2.getProduct().getId().toString()).quantity(orderItem2.getQuantity().toString()).build();
        orderItemDtos.add(orderItemDto);
        orderItemDtos.add(orderItemDto2);
        //when(orderRepository.findById(1L).orElseThrow(IllegalArgumentException::new)).thenReturn(orders.get(0));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));
        when(orderRepository.save(order)).thenReturn(orders.get(0));
        Order createdOrder = orderService.create(orderItemDtos);
        assertEquals(2, createdOrder.getOrderItems().size());
        assertEquals(BigDecimal.valueOf(26.50), createdOrder.getValue());
        verify(orderRepository, times(1)).save(any(Order.class));
    }


}