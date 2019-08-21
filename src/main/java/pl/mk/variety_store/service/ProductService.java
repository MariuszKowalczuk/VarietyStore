package pl.mk.variety_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public ProductDto findById(String id) {
        return productRepository.findById(Long.valueOf(id)).orElseThrow(ResourceNotFoundException::new).toDto();
    }

    @Transactional
    public Product update(String id, ProductDto productDto) {
        Product product = productRepository.findById(Long.valueOf(id)).orElseThrow(ResourceNotFoundException::new);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        return product;

    }
}
