package pl.mk.variety_store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mk.variety_store.dto.NewProductDto;
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

    @PostMapping("/products/new")
    public Product createProduct(@RequestBody NewProductDto productDto) {
        return productService.create(productDto);

    }

    @GetMapping("/products")
    public List<ProductDto> showProducts() {
        return productService.findAll();

    }

    @GetMapping("products/{id}")
    public ProductDto findProductById(@PathVariable String id) {
        return productService.findById(id);

    }

    @PutMapping("products/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        return productService.update(id, productDto);

    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.delete(id);
    }
}
