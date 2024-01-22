package ru.javaops.bootjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import ru.javaops.bootjava.CurrentDateTimeProvider;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@TestComponent
@Profile("test")
public class TestCurrentDateTimeProvider implements CurrentDateTimeProvider {

    @Autowired
    private Environment environment;

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Override
    public LocalTime getCurrentTime() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (Arrays.asList(activeProfiles).contains("normal")) {
            return LocalTime.of(10, 0, 0);
        } else if (Arrays.asList(activeProfiles).contains("error")) {
            return LocalTime.of(13, 0, 0);
        }
        return LocalTime.now();
    }
}