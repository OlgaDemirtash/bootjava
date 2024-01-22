package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "menu")
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedEntityGraph(name = "graph.menuItemWithRestaurant", attributeNodes = @NamedAttributeNode("restaurant"))

public class MenuItem extends NamedEntity {

    @Column(name = "issued", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate issued = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", columnDefinition = "INTEGER", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"votes", "menuItems"})
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    public MenuItem(Integer id, String name, LocalDate issued, Restaurant restaurant, Integer price) {
        super(id, name);
        this.issued = issued;
        this.restaurant = restaurant;
        this.price = price;
    }
}