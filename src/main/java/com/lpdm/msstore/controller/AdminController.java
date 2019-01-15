package com.lpdm.msstore.controller;

import com.lpdm.msstore.repository.StoreRepository;
import com.lpdm.msstore.model.Store;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final Logger log = LogManager.getLogger(StoreController.class);

    private final StoreRepository storeDao;

    @Autowired
    public AdminController(StoreRepository storeDao) {
        this.storeDao = storeDao;
    }

    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Store save(@Valid @RequestBody Store store){

        return storeDao.save(store);
    }
    
    @PostMapping(value = "/delete",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Store> delete(@Valid @RequestBody Store store){
        
        storeDao.delete(store);
        return storeDao.findAll();
    }
}
