package com.vincent.swagger2.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "GreetingController", description = "類別檔案說明")
public class GreetingController {

    @ApiOperation(value = "My first api", notes = "API說明文字")
    @PostMapping("/api/v1")
    public String hello(@PathVariable String dto) {
        System.out.println(dto);
        return "Hello World";
    }
}