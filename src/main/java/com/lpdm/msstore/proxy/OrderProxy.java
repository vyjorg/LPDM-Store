package com.lpdm.msstore.proxy;

import com.lpdm.msstore.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "ms-order",
        url = "${microservice.domain:https://order.lpdm.kybox.fr}")
public interface OrderProxy {

    @RequestMapping(value = "orders/{id}",
            method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Order findOrderById(@PathVariable(value = "id") int id);
}
