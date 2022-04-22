package com.example.demo.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping(path = "test")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class TestController {
    private final Properties properties = new Properties();
    private final TestConfig testConfig;

    @Value("${name}")
    private String name;

    public TestController(TestConfig testConfig) {
        this.testConfig = testConfig;
    }

    @GetMapping
    public String getConfigValue() {
        return testConfig.getTitle();
    }
}
