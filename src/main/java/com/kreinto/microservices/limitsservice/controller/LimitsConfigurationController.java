package com.kreinto.microservices.limitsservice.controller;

import com.kreinto.microservices.limitsservice.bean.Configuration;
import com.kreinto.microservices.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration getLimitsFromConfiguration() {
        return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }

    @GetMapping("/limits-hystrix")
    @HystrixCommand(fallbackMethod="fallbackGetConfiguration")
    public LimitConfiguration getLimitsHystrixFromConfiguration() {
        throw new RuntimeException("Not available");

    }

    public LimitConfiguration fallbackGetConfiguration() {
        return new LimitConfiguration(5, 555);
    }
}
