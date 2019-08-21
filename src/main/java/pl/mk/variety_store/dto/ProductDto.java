package pl.mk.variety_store.dto;

import lombok.*;
import pl.mk.variety_store.model.entity.Product;

import java.math.BigDecimal;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {
    private Long id;
    private BigDecimal price;
    private String name;
    private String description;
    private String category;

    public Product toEntity() {
        return Product.builder().price(price).name(name).description(description).build();
    }
}
