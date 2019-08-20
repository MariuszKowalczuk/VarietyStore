package pl.mk.variety_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mk.variety_store.model.entity.Product;

/**
 * @author Mariusz Kowalczuk
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
