package pl.mk.variety_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mk.variety_store.model.entity.Order;

/**
 * @author Mariusz Kowalczuk
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
