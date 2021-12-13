package org.ml.shipping.dao;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ml.shipping.JsonUtil;
import org.ml.shipping.ShippingApplication;
import org.ml.shipping.entity.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShippingApplication.class)
@AutoConfigureMockMvc
public class ControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductStockDAO productStockRepository;

    @After
    public void resetDb() {
        productStockRepository.deleteAll();
    }


    @Test
    public void whenGetProducts_thenStatus200()
            throws Exception {

        mvc.perform(get("/products?deposit=AL-04-02-DE&location=AR01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenFindProducts_thenStatus200()
            throws Exception {

        mvc.perform(get("/products/find?deposit=AL-04-02-DE&productId=MLA8137271")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenValidInput_thenCreateProductStock()
            throws Exception {
        ProductStock ps = createProduct();
        mvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(ps)));
        List<ProductStock> products = productStockRepository.findAll();
        assertThat(products).extracting(ProductStock::getProductId).containsOnly("MLA8137271");
    }


    ProductStock createProduct(){
        ProductStock ps = new ProductStock();
        ps.setQuantity(1);
        ps.setProductId("MLA8137271");
        ps.setDeposit("AL-04-02-DE");
        ps.setLocation("AR01");
        return productStockRepository.save(ps);
    }

}
