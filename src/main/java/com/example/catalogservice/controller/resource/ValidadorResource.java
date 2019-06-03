package com.example.catalogservice.controller.resource;

import com.example.catalogservice.service.ValidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ValidadorResource {

    @Autowired
    private ValidadorService validadorService;

    @GetMapping(path = "/validate")
    public String validate(@RequestHeader(name = "X-No-Fail", required = false) String noFail) {
        if (StringUtils.isEmpty(noFail)) {
            validadorService.validate(false);
        }
        return validadorService.validate(true);
    }

}
