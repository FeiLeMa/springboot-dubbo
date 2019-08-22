package com.alag.dubbo.client.controller;

import com.alag.dubbo.api.FunckService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Reference
    private FunckService funckService;

    @RequestMapping("index")
    public String index() {
       return funckService.sayHello();
    }
}
