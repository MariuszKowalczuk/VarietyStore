package pl.mk.variety_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mk.variety_store.model.entity.Category;

import java.util.Optional;

/**
 * @author Mariusz Kowalczuk
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
