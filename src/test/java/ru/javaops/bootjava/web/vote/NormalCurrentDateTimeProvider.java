package ru.javaops.bootjava.web.vote;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.javaops.bootjava.CurrentDateTimeProvider;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Profile("normal")
public class NormalCurrentDateTimeProvider implements CurrentDateTimeProvider {

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Override
    public LocalTime getCurrentTime() {
        return LocalTime.of(10, 0, 0);
    }
}