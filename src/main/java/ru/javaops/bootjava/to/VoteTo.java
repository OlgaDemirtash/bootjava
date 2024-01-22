package ru.javaops.bootjava.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.bootjava.model.BaseEntity;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseEntity {

    @NotNull
    int restaurantId;

    LocalDate registered;

    LocalTime registeredTime;

    @ConstructorProperties({"id", "restaurant", "registered"})
    public VoteTo(Integer id, int restaurantId, LocalDate registered, LocalTime registeredTime) {
        super(id);
        this.restaurantId = restaurantId;
        this.registered = registered;
        this.registeredTime = registeredTime;
    }
}