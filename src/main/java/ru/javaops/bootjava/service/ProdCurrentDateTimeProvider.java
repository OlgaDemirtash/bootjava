package ru.javaops.bootjava.service;

import org.springframework.stereotype.Component;
import ru.javaops.bootjava.CurrentDateTimeProvider;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ProdCurrentDateTimeProvider implements CurrentDateTimeProvider {
    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Override
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }
}