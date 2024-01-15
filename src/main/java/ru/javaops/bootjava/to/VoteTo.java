package ru.javaops.bootjava.to;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.javaops.bootjava.model.BaseEntity;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@Getter
@Setter
public class VoteTo extends BaseEntity {

    @NotNull
    private int restaurantId;

    @ConstructorProperties({"id", "restaurant"})
    public VoteTo(Integer id, int restaurantId) {
        super(id);
        this.restaurantId = restaurantId;
    }
}