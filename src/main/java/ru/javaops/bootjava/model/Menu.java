package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Table(name = "menu")
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends NamedEntity {

    @Column(name = "registered", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate registered = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    public Menu(Integer id, String name, Restaurant restaurant, Integer price) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
    }
}