package pl.mk.variety_store.model.entity;

import lombok.*;
import pl.mk.variety_store.dto.CategoryDto;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @OneToMany
    private Set<Product> products;

    public CategoryDto toDto() {
        return CategoryDto.builder().id(id).description(description).build();
    }
}
