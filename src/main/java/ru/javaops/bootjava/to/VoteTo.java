package ru.javaops.bootjava.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import ru.javaops.bootjava.model.BaseEntity;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseEntity {

    @NotNull
    int restaurantId;

    @ConstructorProperties({"id", "restaurant"})
    public VoteTo(Integer id, int restaurantId) {
        super(id);
        this.restaurantId = restaurantId;
    }
}