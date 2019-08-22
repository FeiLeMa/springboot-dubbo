package com.alag.dubbo.server.service.impl;

import com.alag.dubbo.api.FunckService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = FunckService.class)
public class ProviderServiceImpl implements FunckService {
    @Override
    public String sayHello() {
        return "SAONIMA";
    }
}
