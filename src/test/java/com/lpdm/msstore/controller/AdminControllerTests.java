package com.lpdm.msstore.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpdm.msstore.dao.StoreRepository;
import com.lpdm.msstore.model.Location;
import com.lpdm.msstore.model.Store;
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
@WebMvcTest(AdminController.class)
public class AdminControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StoreRepository storeRepository;

    private Store store;

    @Before
    public void init(){

        store = new Store();
        store.setId(1);
        store.setName("MyStore");
        store.setAddressId(1);
        store.setLocation(new Location());
    }

    @Test
    public void saveTest() throws Exception {

        Mockito.when(storeRepository.save(Mockito.any(Store.class))).thenReturn(store);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/admin/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjToJson.get(store));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(ObjToJson.get(store)))
                .andDo(print())
                .andReturn();

        Mockito.verify(storeRepository, Mockito.times(1))
                .save(Mockito.any(Store.class));
    }

    @Test
    public void deleteTest() throws Exception {

        List<Store> storeList = new ArrayList<>();
        storeList.add(store); storeList.add(store);

        Mockito.when(storeRepository.findAll()).thenReturn(storeList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/admin/delete")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjToJson.get(store));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(ObjToJson.get(storeList)))
                .andDo(print())
                .andReturn();
    }
}
