package com.example.demo.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:messages.properties")
public class TestConfig {
    @Value("${title}")
    private String title;

    public String getTitle() {
        return title;
    }
}
