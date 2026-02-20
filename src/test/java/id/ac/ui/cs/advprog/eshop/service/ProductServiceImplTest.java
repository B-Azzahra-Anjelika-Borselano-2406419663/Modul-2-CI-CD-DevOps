package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        verify(productRepository, times(1)).create(product);
        assertEquals(product, result);
    }

    @Test
    void testFindAll() {
        Product product2 = new Product();
        product2.setProductId("456");
        product2.setProductName("Second Product");
        product2.setProductQuantity(20);

        List<Product> mockList = Arrays.asList(product, product2);
        Iterator<Product> mockIterator = mockList.iterator();

        when(productRepository.findAll()).thenReturn(mockIterator);

        List<Product> result = productService.findAll();

        verify(productRepository, times(1)).findAll();
        assertEquals(2, result.size());
        assertEquals(product, result.get(0));
        assertEquals(product2, result.get(1));
    }

    @Test
    void testFindById() {
        when(productRepository.findById("123")).thenReturn(product);

        Product result = productService.findById("123");

        verify(productRepository, times(1)).findById("123");
        assertEquals(product, result);
    }

    @Test
    void testEdit() {
        Product edited = new Product();
        edited.setProductId("123");
        edited.setProductName("Edited Product");
        edited.setProductQuantity(99);

        when(productRepository.edit("123", edited)).thenReturn(product);

        Product result = productService.edit("123", edited);

        verify(productRepository, times(1)).edit("123", edited);
        assertEquals(edited, result);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete("123");

        productService.delete("123");

        verify(productRepository, times(1)).delete("123");
    }
}