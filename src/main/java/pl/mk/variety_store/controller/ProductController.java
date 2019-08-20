package pl.mk.variety_store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mk.variety_store.dto.ProductDto;
import pl.mk.variety_store.model.entity.Product;
import pl.mk.variety_store.service.ProductService;

import java.util.List;

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

    @RequestMapping("/products")
    @GetMapping
    public List<ProductDto> showProducts() {
        return productService.findAll();

    }
}
