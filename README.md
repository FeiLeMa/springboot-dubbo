# springboot-dubbo
## springboot集成dubbo
### 项目目录结构
![04d004feb82552b694c74c09c7ee7234.png](evernotecid://D5BE263E-909D-40FB-BCE6-FB9C6FCE7B6E/appyinxiangcom/25591616/ENResource/p178)

### maven
```java
        <!--dubbo 依赖 -->
        <dependency>
            <groupId>com.alibaba.spring.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>2.0.0</version>
        </dependency>
        <!-- zookeeper client依赖 -->
        <dependency>
            <groupId>com.101tec</groupId>
            <artifactId>zkclient</artifactId>
            <version>0.10</version>
        </dependency>
```

### 提供者
#### yml
```yml
server:
  port: 9391
spring:
  dubbo:
    application:
      name: provider
    server: true
    registry:
      address: zookeeper://47.240.11.229:2181
    protocol:
      name: dubbo
      port: 20880
```
#### ServiceImpl
```java
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

```
#### Springboot启动类
```java
package com.alag.dubbo.server;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class ProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class, args);
    }
}

```

### 消费者
#### yml
```yml
server:
  port: 9392

spring:
  dubbo:
    application:            #应用配置，用于配置当前应用信息，不管该应用是提供者还是消费者。
      name: consumer
    registry:                 #注册中心配置，用于配置连接注册中心相关信息。
      address: zookeeper://47.240.11.229:2181
    protocol:     #协议配置，用于配置提供服务的协议信息，协议由提供方指定，消费方被动接受（订阅）。
      name: dubbo
      port: 20880
    consumer:
      check: false
    reference:
      loadbalance: roundrobin #轮询机制
      #loadbalance: random #随机机制
      #loadbalance: leastactive #最少活跃调用数机制
```

#### Controller
```java
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

```
#### 启动类
```java
package com.alag.dubbo.client.controller;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class ClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }
}

```

