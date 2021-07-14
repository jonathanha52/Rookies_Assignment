package rookies.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

import rookies.demo.repository.ProductRepository;
import rookies.demo.service.impl.ProductService;
import rookies.demo.model.Product;
import rookies.demo.model.Category;

import rookies.demo.utils.ConvertJSONString;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {
    private final Long VALID_ID = 1L;
    private final Category TEST_CATEGORY = new Category(1, "test category","this is test category");
    private final Date TEST_DATE = new Date();
    private final String TEST_UNIT = "test unit";
    private final Product VALID_PRODUCT = new Product(
        VALID_ID, 
        "valid product", 
        "this is a test product",
        1.5,
        TEST_UNIT,
        TEST_CATEGORY,
        TEST_DATE,
        TEST_DATE);
    private final ArrayList<Product> PRODUCT_LIST = new ArrayList<>(Arrays.asList(VALID_PRODUCT));
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    @WithMockUser(username="user1",roles = "CUSTOMER")
    public void testAddProduct_WrongAuth() throws Exception{
        this.mockMvc.perform(post("/api/v1/product")
                    .content(ConvertJSONString.ObjToJSON(VALID_PRODUCT))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(username="admin", roles="ADMIN")
    public void testAddProduct_RightAuth() throws Exception{
        this.mockMvc.perform(post("/admin/product/")
                    .content(ConvertJSONString.ObjToJSON(VALID_PRODUCT))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(username="admin", roles="ADMIN")
    public void testDeleteProduct_RightAuth() throws Exception{
        this.mockMvc.perform(delete("/admin/product/"+VALID_ID)
                    .content(ConvertJSONString.ObjToJSON(VALID_PRODUCT))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles="CUSTOMER")
    public void testDeleteProduct_WroneAuth() throws Exception{
        this.mockMvc.perform(delete("/admin/product/"+VALID_ID)
                    .content(ConvertJSONString.ObjToJSON(VALID_PRODUCT))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }
    
    @Test
    public void testFindAllProduct() throws Exception{
        when(productService.findAll()).thenReturn(PRODUCT_LIST);
        this.mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(PRODUCT_LIST.size())));
    }
}
