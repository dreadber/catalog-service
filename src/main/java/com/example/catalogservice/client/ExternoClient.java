package com.example.catalogservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("externo-service")
public interface ExternoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/externo", consumes = "application/json")
    String obtenerExterno();

}
