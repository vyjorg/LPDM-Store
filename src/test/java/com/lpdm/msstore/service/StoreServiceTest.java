package com.lpdm.msstore.service;

import com.lpdm.msstore.exception.StoreNotFoundException;
import com.lpdm.msstore.model.Store;
import com.lpdm.msstore.model.location.Address;
import com.lpdm.msstore.proxy.LocationProxy;
import com.lpdm.msstore.repository.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    @MockBean
    private LocationProxy locationProxy;

    @MockBean
    private StoreRepository storeRepository;

    private int randomInt;
    private Store store;
    private List<Store> storeList;

    @Before
    public void init(){

        randomInt = (int) (Math.random()*123);

        store = new Store();
        store.setAddressId(randomInt);

        storeList = new ArrayList<>();
        storeList.add(store);
    }

    @Test(expected = StoreNotFoundException.class)
    public void findStoreByIdException() {

        when(storeRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        storeService.findStoreById(randomInt);
    }

    @Test
    public void findStoreById(){

        when(storeRepository.findById(anyInt()))
                .thenReturn(Optional.of(store));

        when(locationProxy.findAddressById(anyInt()))
                .thenReturn(null);

        assertEquals(store, storeService.findStoreById(randomInt));
    }

    @Test(expected = StoreNotFoundException.class)
    public void findStoreByNameException() {

        when(storeRepository.findAllByNameContainingIgnoreCase(anyString()))
                .thenReturn(new ArrayList<>());

        storeService.findStoreByName("test");
    }

    @Test
    public void findStoreByName() {

        when(storeRepository.findAllByNameContainingIgnoreCase(anyString()))
                .thenReturn(storeList);

        assertEquals(storeList, storeService.findStoreByName("test"));
    }

    @Test(expected = StoreNotFoundException.class)
    public void findAllStoresException() {

        when(storeRepository.findAll())
                .thenReturn(new ArrayList<>());

        storeService.findAllStores();
    }

    @Test
    public void findAllStores() {

        when(storeRepository.findAll())
                .thenReturn(storeList);

        assertEquals(storeList, storeService.findAllStores());
    }

    @Test
    public void addNewStore() {

        when(storeRepository.save(any(Store.class)))
                .thenReturn(store);

        assertEquals(store, storeService.addNewStore(store));
    }

    @Test(expected = StoreNotFoundException.class)
    public void deleteStoreException() {

        when(storeRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        storeService.deleteStore(store);
    }

    @Test
    public void deleteStore() {

        when(storeRepository.findById(anyInt()))
                .thenReturn(Optional.of(store))
                .thenReturn(Optional.empty());

        assertTrue(storeService.deleteStore(store));
    }

    @Test(expected = StoreNotFoundException.class)
    public void updateStoreException() {

        when(storeRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        storeService.updateStore(store);
    }

    @Test
    public void updateStore() {

        when(storeRepository.findById(anyInt()))
                .thenReturn(Optional.of(store));

        when(storeRepository.save(any(Store.class)))
                .thenReturn(store);

        assertEquals(store, storeService.updateStore(store));
    }
}
