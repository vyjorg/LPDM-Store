package com.lpdm.msstore.controller;

import com.lpdm.msstore.dao.StoreRepository;
import com.lpdm.msstore.model.Location;
import com.lpdm.msstore.model.Store;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final Logger log = LogManager.getLogger(StoreController.class);

    private final StoreRepository storeDao;
    private final LocationController locationController;

    @Autowired
    public StoreController(StoreRepository storeDao, LocationController locationController) {
        this.storeDao = storeDao;
        this.locationController = locationController;
    }

    @GetMapping(value = {"", "/", "/all"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Store> getAllStores(){

        return storeDao.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Store getStoreById(@PathVariable int id){

        Optional<Store> optionalStore = storeDao.findById(id);

        if(optionalStore.isPresent()){

            Store store = optionalStore.get();

            Location location = locationController.findLocationById(store.getAddressId());
            if(location != null) location.setId(store.getAddressId());
            else log.warn("Location object is null");
            store.setLocation(location);

            return store;
        }
        else {
            log.warn("Store object is null");
            return null;
        }
    }
}