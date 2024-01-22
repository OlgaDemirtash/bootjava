package ru.javaops.bootjava.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class MenuItemTo extends NamedTo {

    LocalDate issued;

    Integer restaurantId;

    Integer price;

    @ConstructorProperties({"id", "name", "issued", "restaurantId", "price"})
    public MenuItemTo(Integer id, String name, LocalDate issued, Integer restaurantId, Integer price) {
        super(id, name);
        this.issued = issued;
        this.restaurantId = restaurantId;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItemTo:" + id + "[" + restaurantId + " / " + name + " / " + price + "]";
    }
}