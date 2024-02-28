package com.springtesting.junit.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.springtesting.junit.controllers.ProductController;
import com.springtesting.junit.models.Product;
import com.springtesting.junit.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    Product record1 = new Product("1", "Television", 20000d);
    Product record2 = new Product("2", "Chair",  400d);
    Product record3 = new Product("3", "Laptop",  50000d);

    @BeforeEach
    public void setup() {
//        MockitoAnnotations.initMocks(this); // response Body = []  --> Assertion if failed
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void getAllRecords_success() throws Exception {
        List<Product> records = Arrays.asList(record1, record2, record3);
        Mockito.when(productService.getAllProducts()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("Laptop")));
    }

    @Test
    public void getProductById_success() throws Exception {
        Mockito.when(productService.getProductByProductId(Mockito.anyString())).thenReturn(record1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Television")));
    }

    @Test
    public void createRecord_success() throws Exception {
        Product product = Product.builder()
                .productId("1")
                .name("Television")
                .price(100d)
                .build();

        Mockito.when(productService.createProduct(Mockito.any(Product.class))).thenReturn(product);
        String content = objectWriter.writeValueAsString(product);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        System.out.print(mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is("Television"))));
    }

    @Test
    public void updateProductRecord_success() throws Exception {
        Product updateProductRecord = Product.builder()
                .productId("1")
                .name("Radio")
                .price(100d)
                .build();

        Mockito.when(productService.updateProduct( Mockito.any(Product.class))).thenReturn( updateProductRecord);
        String updatedContent = objectWriter.writeValueAsString(updateProductRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is("Radio")));
    }

    @Test
    public void deleteProductById_success() throws Exception {
        Mockito.doNothing().when(productService).deleteProduct(Mockito.anyLong()); //mocking void method

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/products/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

