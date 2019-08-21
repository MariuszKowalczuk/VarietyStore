package pl.mk.variety_store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Mariusz *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewProductDto {
    private BigDecimal price;
    private String name;
    private String productDescription;
    private String categoryDescription;     //in incoming JSON, this String must be lowercased

}
