package com.lpdm.msstore.controller;

import com.lpdm.msstore.model.Location;
import com.lpdm.msstore.proxy.LocationProxy;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final Logger log = LogManager.getLogger(LocationController.class);

    private final LocationProxy locationProxy;

    @Autowired
    public LocationController(LocationProxy locationProxy) {
        this.locationProxy = locationProxy;
    }

    @GetMapping("/{id}")
    public Location findLocationById(@PathVariable int id){

        Location location = null;
        try{ location = locationProxy.findLocationById(id); }
        catch (FeignException e){ log.error(e.getMessage()); }
        return location;
    }
}