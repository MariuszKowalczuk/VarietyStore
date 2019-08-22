package pl.mk.variety_store.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import pl.mk.variety_store.dto.NewProductDto;
import pl.mk.variety_store.dto.ProductDto;
import pl.mk.variety_store.model.entity.Category;
import pl.mk.variety_store.model.entity.Product;
import pl.mk.variety_store.repository.CategoryRepository;
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
    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository, categoryRepository);
    }

    @Test
    public void createProductShouldCreateProduct() {
        NewProductDto newProductDto = new NewProductDto();
        newProductDto.setName("ball");
        newProductDto.setProductDescription("bouncy");
        Product product = new Product();
        product.setName(newProductDto.getName());
        product.setDescription(newProductDto.getProductDescription());
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto e = product.toDto();
        productDtos.add(e);
        when(productRepository.save(product)).thenReturn(productDtos.get(0).toEntity());
        Product product1 = productService.create(newProductDto);
        assertEquals("ball", product1.getName());
        assertEquals("bouncy", product1.getDescription());
        verify(productRepository).save(any(Product.class));

    }

    @Test
    public void creationOfProductShouldCreateACategoryIfItHasntExisted() {
        NewProductDto newProductDto = new NewProductDto();
        newProductDto.setName("ball");
        newProductDto.setCategoryDescription("toys");
        Product product = new Product();
        product.setName(newProductDto.getName());
        product.setCategory(Category.builder().description(newProductDto.getCategoryDescription()).build());
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto e = product.toDto();
        productDtos.add(e);
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        categories.add(category);
        category.setDescription(newProductDto.getCategoryDescription());
        when(productRepository.save(product)).thenReturn(productDtos.get(0).toEntity());
        when(categoryRepository.save(category)).thenReturn(categories.get(0));
        when(productRepository.findAll()).thenReturn(productDtos.stream().map(ProductDto::toEntity).collect(Collectors.toList()));
        when(categoryRepository.findAll()).thenReturn(categories);
        Product product1 = productService.create(newProductDto);
        assertEquals("ball", product1.getName());
        assertEquals(1, productRepository.findAll().size());
        assertEquals(1, categoryRepository.findAll().size());
        verify(productRepository).save(any(Product.class));
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    public void creationOfProductShouldNotCreateACategoryIfItHasExisted() {
        NewProductDto newProductDto = new NewProductDto();
        newProductDto.setName("ball");
        newProductDto.setCategoryDescription("toys");
        NewProductDto newProductDto2 = new NewProductDto();
        newProductDto2.setName("robot");
        newProductDto2.setCategoryDescription("toys");
        Product product = new Product();
        Product product2 = new Product();
        product.setName(newProductDto.getName());
        product.setCategory(Category.builder().description(newProductDto.getCategoryDescription()).build());
        product2.setName(newProductDto2.getName());
        product2.setCategory(Category.builder().description(newProductDto2.getCategoryDescription()).build());
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto productDto = product.toDto();
        productDtos.add(productDto);
        ProductDto productDto2 = product2.toDto();
        productDtos.add(productDto2);
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        categories.add(category);
        category.setDescription(newProductDto.getCategoryDescription());
        when(productRepository.save(product)).thenReturn(productDtos.get(0).toEntity());
        when(productRepository.save(product2)).thenReturn(productDtos.get(1).toEntity());
        when(categoryRepository.save(category)).thenReturn(categories.get(0));
        when(productRepository.findAll()).thenReturn(productDtos.stream().map(ProductDto::toEntity).collect(Collectors.toList()));
        when(categoryRepository.findAll()).thenReturn(categories);
        Product product1 = productService.create(newProductDto);
        assertEquals("ball", product1.getName());
        assertEquals(2, productRepository.findAll().size());
        assertEquals(1, categoryRepository.findAll().size());
        verify(productRepository).save(any(Product.class));
        verify(categoryRepository, times(1)).save(any(Category.class));
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

    @Test(expected = ResourceNotFoundException.class)
    public void findByIdShouldFindProductWithSpecifiedId() {
        Product product = new Product();
        product.setName("ball");
        product.setId(1L);
        Product product1 = new Product();
        product1.setName("glasses");
        product1.setId(2L);
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(product.toDto());
        productDtos.add(product1.toDto());
        when(productRepository.findById(1L)).thenReturn(productDtos.stream().filter(productDto -> productDto.getId().equals(1L)).map(ProductDto::toEntity).findFirst());
        when(productRepository.findById(2L)).thenReturn(productDtos.stream().filter(productDto -> productDto.getId().equals(2L)).map(ProductDto::toEntity).findFirst());
        doThrow(ResourceNotFoundException.class).when(productRepository).findById(3L);
        productService.findById("3");
        assertEquals("ball", productService.findById("1").getName());
        assertEquals("glasses", productService.findById("2").getName());

        verify(productRepository, times(2)).findById(any(Long.class));
    }

    @Test
    public void updateProductShouldChangeProductProperties() {
        Product product = new Product();
        product.setName("ball");
        product.setDescription("bouncy");
        product.setId(1L);
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(product.toDto());
        when(productRepository.findById(1L)).thenReturn(productDtos.stream().filter(productDto -> productDto.getId().equals(1L)).map(ProductDto::toEntity).findFirst());
        ProductDto dtoToUpdate = ProductDto.builder().name("not a ball at all").description("flat").build();
        Product update = productService.update("1", dtoToUpdate);
        assertEquals("not a ball at all", productRepository.findById(1L).get().getName());
        assertEquals("flat", productRepository.findById(1L).get().getDescription());
        assertNotNull(productRepository.findById(1L).get().getDescription());
        verify(productRepository, atLeastOnce()).findById(any(Long.class));
    }

    @Test
    public void shouldDeleteProductOfSpecifiedId() {
        String id = "1";
        productService.delete("1");
        verify(productRepository, times(1)).deleteById(eq(Long.valueOf(id)));


    }


}