package com.lpdm.msstore.controller;

import com.lpdm.msstore.exception.StoreNotFoundException;
import com.lpdm.msstore.model.Store;
import com.lpdm.msstore.service.StoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final Logger log = LogManager.getLogger(StoreController.class);

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {

        this.storeService = storeService;
    }

    /**
     * Find all {@link Store} in the database
     * @return All {@link Store} found
     * @throws StoreNotFoundException Thrown if no {@link Store} was found
     */
    @GetMapping(value = {"", "/", "/all"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Store> getAllStores() throws StoreNotFoundException {

        return storeService.findAllStores();
    }

    /**
     * Find a {@link Store} by its id
     * @param id The {@link Store} id
     * @return The {@link Store} object found
     * @throws StoreNotFoundException Thrown if no {@link Store} was found
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Store getStoreById(@PathVariable int id) throws StoreNotFoundException {

        return storeService.findStoreById(id);
    }

    /**
     * Find a {@link Store} by its name
     * @param name The {@link Store} name
     * @return A {@link List} of {@link Store} objects found
     * @throws StoreNotFoundException Thrown if no {@link Store} was found
     */
    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Store> getStoreByName(@PathVariable String name) throws StoreNotFoundException {

        return storeService.findStoreByName(name);
    }
}