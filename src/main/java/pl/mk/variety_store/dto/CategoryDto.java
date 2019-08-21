package pl.mk.variety_store.dto;

import lombok.*;
import pl.mk.variety_store.model.entity.Category;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String description;

    public Category toEntity() {
        return Category.builder().description(description).build();
    }
}
