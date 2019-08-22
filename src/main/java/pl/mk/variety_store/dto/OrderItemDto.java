package pl.mk.variety_store.dto;

import lombok.*;

/**
 * @author Mariusz Kowalczuk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    String productId;
    String quantity;
}
