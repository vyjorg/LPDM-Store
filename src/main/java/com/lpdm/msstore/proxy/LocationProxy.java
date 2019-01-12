package com.lpdm.msstore.proxy;

import com.lpdm.msstore.model.Location;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "zuul-server", url = "https://zuul.lpdm.kybox.fr")
@RibbonClient("ms-location")
public interface LocationProxy {

    @RequestMapping(value = "/locations/{id}",
            method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Location findLocationById(@PathVariable(value = "id") int id);

}