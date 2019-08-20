package pl.mk.variety_store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mk.variety_store.dto.ProductDto;
import pl.mk.variety_store.model.entity.Product;
import pl.mk.variety_store.service.ProductService;

/**
 * @author Mariusz Kowalczuk
 */
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @RequestMapping("/products/new")
    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.create(productDto);

    }
}
