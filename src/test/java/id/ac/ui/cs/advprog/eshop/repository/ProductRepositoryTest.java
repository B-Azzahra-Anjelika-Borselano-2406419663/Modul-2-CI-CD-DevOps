package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    private Product createProduct(String id, String name, int quantity) {
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductQuantity(quantity);
        return product;
    }

    private Product getFirstProduct() {
        Iterator<Product> iterator = productRepository.findAll();
        return iterator.hasNext() ? iterator.next() : null;
    }

    @Test
    void testCreateAndFind() {
        Product product = createProduct("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        productRepository.create(product);
        Product savedProduct = getFirstProduct();

        assertNotNull(savedProduct);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = createProduct("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        Product product2 = createProduct("a0f9de46-90b1-437d-a0bf-d0821dde9096", "Sampo Cap Usep", 50);
        productRepository.create(product1);
        productRepository.create(product2);

        Iterator<Product> iterator = productRepository.findAll();

        assertTrue(iterator.hasNext());
        assertEquals(product1.getProductId(), iterator.next().getProductId());
        assertEquals(product2.getProductId(), iterator.next().getProductId());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product original = createProduct("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        productRepository.create(original);

        Product updated = createProduct("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bango", 999);
        productRepository.edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updated);

        Product editedProduct = getFirstProduct();

        assertNotNull(editedProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", editedProduct.getProductId());
        assertEquals("Sampo Cap Bango", editedProduct.getProductName());
        assertEquals(999, editedProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = createProduct("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        productRepository.create(product);
        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testFindIdNotExist() {
        Product product1 = createProduct("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        Product product2 = createProduct("a976de46-90b1-437d-a0bf-d0821dde2406", "Sampo Cap Usep", 50);
        productRepository.create(product1);
        productRepository.create(product2);
        Product product = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertNull(product);
    }

    @Test
    void testSetRandomUUID() {
        Product product = new Product();
        product.setProductQuantity(10);
        product.setProductName("Sampo");

        productRepository.create(product);
        Product savedProduct = getFirstProduct();

        assertNotNull(savedProduct.getProductId());
    }

    @Test
    void testEditProductNotExist() {
        Product product = createProduct("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        Product edited = productRepository.edit("a0f9de46-90b1-437d-a0bf-d0821dde9096", product);

        assertNull(edited);
    }
}