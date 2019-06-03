package com.example.catalogservice.service;

import com.example.catalogservice.client.ExternoClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorService {

    @Autowired
    private ExternoClient externoClient;

    @HystrixCommand(fallbackMethod = "validateFallback")
    public String validate(boolean fail) {
        if (fail) {
            throw new RuntimeException();
        }
        return externoClient.obtenerExterno();
    }

    private String validateFallback(boolean fail) {
        return "fallback";
    }

}
