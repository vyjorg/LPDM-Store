package com.lpdm.msstore.controller;

import com.lpdm.msstore.model.Order;
import com.lpdm.msstore.model.OrderedProduct;
import com.lpdm.msstore.model.Product;
import com.lpdm.msstore.model.User;
import com.lpdm.msstore.proxy.OrderProxy;
import com.lpdm.msstore.utils.ObjToJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderProxy orderProxy;

    private Order order;

    @Before
    public void init(){

        order = new Order();
        order.setId(1);
        order.setCustomer(new User(1));

        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setOder(order);
        orderedProduct.setProduct(new Product(1));
        orderedProduct.setQuantity(10);
        orderedProduct.setPrice(18.26);

        List<OrderedProduct> productList = new ArrayList<>();
        productList.add(orderedProduct);

        order.setOrderedProducts(productList);
    }

    @Test
    public void findOrderByIdTest() throws Exception {

        Mockito.when(orderProxy.findOrderById(Mockito.anyInt())).thenReturn(order);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/orders/1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(ObjToJson.get(order)))
                .andDo(print());
    }
}
