package rookies.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import rookies.demo.repository.ProductRepository;
import rookies.demo.repository.CategoryRepository;
import rookies.demo.service.impl.ProductService;
import rookies.demo.exception.IdNotFoundException;
import rookies.demo.model.Category;
import rookies.demo.model.Product;
import rookies.demo.model.Rating;

@SpringBootTest
public class ProductServiceTest {
    private final int PAGE = 1;
    private final int ITEM_PER_PAGE = 1;
    private final Long INVALID_ID = -1L;
    private final Long VALID_ID = 1L;
    private final Category TEST_CATEGORY = new Category(1, "test category","this is test category");
    private final Date TEST_DATE = new Date();
    private final String TEST_UNIT = "test unit";
    private final Set<Rating> RATING = new HashSet<>();
    private final Product VALID_PRODUCT = new Product(
        VALID_ID, 
        "valid product", 
        "this is a test product",
        1.5,
        TEST_UNIT,
        TEST_CATEGORY,
        TEST_DATE,
        TEST_DATE,
        RATING);
    private final Product INVALID_PRODUCT = new Product(
        INVALID_ID, 
        "invalid product", 
        "this is a test product",
        1.5,
        TEST_UNIT,
        TEST_CATEGORY,
        TEST_DATE,
        TEST_DATE,
        RATING);
    private final List<Product> TEST_PRODUCT_LIST_ALL = new ArrayList<Product>(Arrays.asList(VALID_PRODUCT, INVALID_PRODUCT));
    private final List<Product> TEST_PRODUCT_LIST_CATEGORY = new ArrayList<Product>(Arrays.asList(VALID_PRODUCT, INVALID_PRODUCT));

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testFindAllProduct(){
        when(productRepository.findAll()).thenReturn(TEST_PRODUCT_LIST_ALL);
        List<Product> result = productService.findAll();
        assertEquals(result.size(), TEST_PRODUCT_LIST_ALL.size());
    }
    @Test
    public void findProductByName(){
        List<Product> test = Arrays.asList(VALID_PRODUCT);
        when(productRepository.findByProductNameContaining("valid product")).thenReturn(test);
        List<Product> result = productService.findProductByName("valid product", PAGE, ITEM_PER_PAGE);
        assertEquals(test.size(), result.size());
    }
    @Test
    public void testFindByProductID_IdNotFound(){
        assertThrows(IdNotFoundException.class, ()->{
            productService.findById(INVALID_ID);
        });
    }

    @Test
    public void testFindByProducId(){
        Optional<Product> result = Optional.of(VALID_PRODUCT);
        when(productRepository.findById(1L)).thenReturn(result);

        Product test = productService.findById(1L);
        assertTrue(VALID_PRODUCT.equals(test));
    }

    @Test
    public void testFindByCategory(){
        when(categoryRepository.findByName(TEST_CATEGORY.getName())).thenReturn(Optional.of(TEST_CATEGORY));
        when(productRepository.findByCategory(TEST_CATEGORY)).thenReturn(TEST_PRODUCT_LIST_CATEGORY);
        List<Product> result = productService.findByCategory(TEST_CATEGORY.getName(), PAGE, ITEM_PER_PAGE);
        assertEquals(result.size(), TEST_PRODUCT_LIST_CATEGORY.size());
    }

    @Test
    public void testUpdateProduct_productNotFound(){
        assertThrows(IdNotFoundException.class, () -> {
            productService.updateProduct(INVALID_ID, INVALID_PRODUCT);
        });
    }

    @Test
    public void testDeleteProduct_productNotFound(){
        assertThrows(IdNotFoundException.class, () -> {
            productService.deleteProduct(INVALID_ID,INVALID_PRODUCT);
        });
    }
}
