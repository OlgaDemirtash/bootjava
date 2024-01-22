package ru.javaops.bootjava;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public interface CurrentDateTimeProvider {
    LocalDate getCurrentDate();

    LocalTime getCurrentTime();
}