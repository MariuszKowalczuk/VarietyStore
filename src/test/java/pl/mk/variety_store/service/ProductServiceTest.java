package pl.mk.variety_store.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mk.variety_store.dto.ProductDto;
import pl.mk.variety_store.model.entity.Product;
import pl.mk.variety_store.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Mariusz Kowalczuk
 */
public class ProductServiceTest {
    private ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    public void createProductShouldCreateProduct() {
        Product product = new Product();
        product.setName("ball");
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto e = product.toDto();
        productDtos.add(e);
        when(productRepository.save(product)).thenReturn(productDtos.get(0).toEntity());
        when(productRepository.findAll()).thenReturn(productDtos.stream().map(ProductDto::toEntity).collect(Collectors.toList()));
        Product product1 = productService.create(e);
        assertEquals("ball", product1.getName());
        assertNull(product1.getDescription());
        verify(productRepository).save(any(Product.class));

    }

    @Test
    public void findAllShouldListAllProducts() {
        Product product = new Product();
        product.setName("ball");
        Product product1 = new Product();
        product1.setName("glasses");
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(product.toDto());
        productDtos.add(product1.toDto());
        when(productRepository.findAll()).thenReturn(productDtos.stream().map(ProductDto::toEntity).collect(Collectors.toList()));
        assertEquals(2, productService.findAll().size());
        assertNotEquals(3, productService.findAll().size());
        verify(productRepository, times(2)).findAll();
    }

}