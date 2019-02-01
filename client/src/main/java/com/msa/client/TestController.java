package com.msa.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/ping")
    public List<ServiceInstance> ping() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLIENT-SERVICE");
        LOGGER.info("INSTANCES: count = {}", instances.size());
        instances.forEach(it -> LOGGER.info("INSTANCE: id={}, prot={}", it.getServiceId(), it.getPort()));
        return instances;
    }



}
