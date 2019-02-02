package com.lpdm.msstore.controller;

import com.lpdm.msstore.model.Store;
import com.lpdm.msstore.service.StoreService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StoreController.class)
public class StoreControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    private Store store;
    private List<Store> storeList;

    @Before
    public void init(){

        store = new Store();
        store.setId(6);
        store.setName("MyStore6");
        store.setAddressId(6);

        storeList = new ArrayList<>();
        storeList.add(store); storeList.add(store);
    }

    @Test
    public void getAllStoresTest() throws Exception {

        Mockito.when(storeService.findAllStores()).thenReturn(storeList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/stores")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(ObjToJson.get(storeList)))
                .andDo(print());
    }

    @Test
    public void getStoreByIdTest() throws Exception {

        Mockito.when(storeService.findStoreById(Mockito.anyInt())).thenReturn(store);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/stores/1")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(ObjToJson.get(store)))
                .andDo(print());
    }
}
