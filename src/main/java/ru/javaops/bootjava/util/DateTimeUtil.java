package ru.javaops.bootjava.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class DateTimeUtil {

    public static LocalDate dayOrMin(LocalDate localDate) {
        return localDate != null ? localDate : LocalDate.MIN;
    }

    public static LocalDate dayOrMax(LocalDate localDate) {
        return localDate != null ? localDate : LocalDate.MAX;
    }
}