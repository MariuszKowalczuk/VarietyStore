package pl.mk.variety_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mk.variety_store.dto.NewProductDto;
import pl.mk.variety_store.dto.ProductDto;
import pl.mk.variety_store.model.entity.Category;
import pl.mk.variety_store.model.entity.Product;
import pl.mk.variety_store.repository.CategoryRepository;
import pl.mk.variety_store.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product create(NewProductDto productDto) {
        Category category;
        Optional<Category> byDescription = categoryRepository.findByDescription(productDto.getCategoryDescription());
        if (byDescription.isPresent()) {
            category = byDescription.get();
        } else {
            category = Category
                    .builder()
                    .description(productDto.getCategoryDescription())
                    .build();
            categoryRepository.save(category);
        }


        Product product = Product
                .builder()
                .name(productDto.getName())
                .description(productDto.getProductDescription())
                .price(productDto.getPrice())
                .category(category)
                .build();
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

    public void delete(String id) {
        productRepository.deleteById(Long.valueOf(id));
    }
}
