package pl.mk.variety_store.model.entity;

import lombok.*;
import pl.mk.variety_store.dto.ProductDto;

import javax.persistence.*;
import java.math.BigDecimal;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private String name;
    private String description;

    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems;


    public ProductDto toDto() {
        return ProductDto.builder().id(id).price(price).name(name).description(description).category(category != null ? category.getDescription() : null).build();
    }
}
