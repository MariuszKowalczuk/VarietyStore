package pl.mk.variety_store.service

import pl.mk.variety_store.model.entity.OrderItem
import pl.mk.variety_store.model.entity.Product
import pl.mk.variety_store.repository.OrderItemRepository
import pl.mk.variety_store.repository.OrderRepository
import pl.mk.variety_store.repository.ProductRepository
import spock.lang.Specification

/**
 * @author Mariusz Kowalczuk
 */
class OrderServiceSpecification extends Specification {
    def "computed value should be correct"() {
        given:
        def orderItem = OrderItem.builder().product(Product.builder().price(15.67).build()).quantity(1).build()
        def orderItem1 = OrderItem.builder().product(Product.builder().price(33.33).build()).quantity(2).build()
        Set<OrderItem> orderItems = new HashSet<>()
        orderItems.add(orderItem)
        orderItems.add(orderItem1)
        OrderService orderService = new OrderService(Mock(OrderRepository.class), Mock(OrderItemRepository.class), Mock(ProductRepository.class))

        when:
        def value = orderService.computeValue(orderItems)

        then:
        value == 82.33
        value != null
        value != 12.12
    }
}
