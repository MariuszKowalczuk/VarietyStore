package pl.mk.variety_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mk.variety_store.model.entity.OrderItem;

/**
 * @author Mariusz Kowalczuk
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
