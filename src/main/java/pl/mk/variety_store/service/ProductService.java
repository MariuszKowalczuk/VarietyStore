package pl.mk.variety_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mk.variety_store.dto.ProductDto;
import pl.mk.variety_store.model.entity.Product;
import pl.mk.variety_store.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(ProductDto productDto) {

        Product product = productDto.toEntity();
        productRepository.save(product);
        return product;


    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(Product::toDto).collect(Collectors.toList());
    }
}
