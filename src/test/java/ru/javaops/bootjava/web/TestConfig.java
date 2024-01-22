package ru.javaops.bootjava.web;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import ru.javaops.bootjava.CurrentDateTimeProvider;

@TestConfiguration
public class TestConfig {
    @Bean
    @Profile("test")
    public CurrentDateTimeProvider currentDateTimeProvider() {
        return new TestCurrentDateTimeProvider();
    }
}