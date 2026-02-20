package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @Test
    void testCreateProductPage() {
        String view = productController.createProductPage(model);
        verify(model).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", view);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String view = productController.createProductPost(product, model);

        verify(productService).create(product);
        assertEquals("redirect:list", view);
    }

    @Test
    void testProductListPage() {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);
        String view = productController.productListPage(model);

        verify(productService).findAll();
        verify(model).addAttribute("products", products);
        assertEquals("productList", view);
    }

    @Test
    void testEditProductPage_ProductExists() {
        String id = "123";
        Product product = new Product();
        when(productService.findById(id)).thenReturn(product);
        String view = productController.editProductPage(id, model);

        verify(productService).findById(id);
        verify(model).addAttribute("product", product);
        verify(model).addAttribute("productId", id);
        assertEquals("EditProduct", view);
    }

    @Test
    void testEditProductPage_ProductNotFound() {
        String id = "123";
        when(productService.findById(id)).thenReturn(null);
        String view = productController.editProductPage(id, model);

        verify(productService).findById(id);
        assertEquals("redirect:/product/list", view);
        verifyNoMoreInteractions(model);
    }

    @Test
    void testEditProductPost() {
        String id = "123";
        Product product = new Product();
        product.setProductId(id);
        String view = productController.editProductPost(id, product, model);

        verify(productService).edit(id, product);
        assertEquals("redirect:/product/list", view);
    }

    @Test
    void testDeleteProductPage() {
        String id = "123";
        String view = productController.deleteProductPage(id, model);

        verify(productService).delete(id);
        assertEquals("redirect:/product/list", view);
    }
}