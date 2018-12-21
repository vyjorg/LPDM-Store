package com.lpdm.msstore.controller;

import com.lpdm.msstore.model.Location;
import com.lpdm.msstore.proxy.LocationProxy;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(LocationController.class)
public class LocationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationProxy locationProxy;

    private int id;
    private Location location;

    @Before
    public void init(){

        id = 45;
        location = new Location();
        location.setId(id);
    }

    @Test
    public void findLocationByIdTest() throws Exception {

        Mockito.when(locationProxy.findLocationById(Mockito.anyInt())).thenReturn(location);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/locations/" + id);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(ObjToJson.get(location)))
                .andDo(print());
    }
}
