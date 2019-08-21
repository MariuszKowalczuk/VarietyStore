package pl.mk.variety_store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mariusz Kowalczuk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    String productId;
    String quantity;
}
