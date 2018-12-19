package com.lpdm.msstore.controller;

import com.lpdm.msstore.model.Order;
import com.lpdm.msstore.proxy.OrderProxy;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final Logger log = LogManager.getLogger(LocationController.class);

    private final OrderProxy orderProxy;

    @Autowired
    public OrderController(OrderProxy orderProxy) {
        this.orderProxy = orderProxy;
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable int id){

        Order order = null;
        try { order = orderProxy.findOrderById(id); }
        catch (FeignException e) { log.error(e.getMessage()); }
        return order;
    }
}
