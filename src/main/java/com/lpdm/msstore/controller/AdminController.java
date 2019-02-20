package com.lpdm.msstore.controller;

import com.lpdm.msstore.exception.StoreNotFoundException;
import com.lpdm.msstore.model.Store;
import com.lpdm.msstore.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Kybox
 * @version 1.0
 * @since 01/12/2018
 */

@RestController
@RequestMapping("/admin")
@Api(tags = {"Admin Rest API"})
public class AdminController {

    private final Logger log = LogManager.getLogger(StoreController.class);

    private final StoreService storeService;

    @Autowired
    public AdminController(StoreService storeService) {

        this.storeService = storeService;
    }

    /**
     * Persist a new {@link Store} object in the database
     * @param store The {@link Store} object to persist
     * @return The {@link Store} object persisted
     */
    @ApiOperation(value = "Persist a new Store object in the database")
    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Store addNewStore(@Valid @RequestBody Store store){

        return storeService.addNewStore(store);
    }

    /**
     * Delete a {@link Store} object in the database
     * @param store The {@link Store} object to delete
     * @return True if the {@link Store} object was deleted, otherwise false
     * @throws StoreNotFoundException Thrown if the {@link Store} object to delete was not found
     */
    @ApiOperation(value = "Delete a Store object in the database")
    @DeleteMapping(value = "/delete",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean delete(@Valid @RequestBody Store store) throws StoreNotFoundException {
        
        return storeService.deleteStore(store);
    }

    /**
     * Update a {@link Store} object in the database
     * @param store The {@link Store} object to update
     * @return The {@link Store} object update
     * @throws StoreNotFoundException Thrown if the {@link Store} object was not found
     */
    @ApiOperation(value = "Update a Store object in the database")
    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Store updateStore(@Valid @RequestBody Store store) throws StoreNotFoundException {

        return storeService.updateStore(store);
    }
}
