package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {

    //@Column(name = "menu_id", nullable = false, updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @EmbeddedId
    private MenuId menuId;

    @Column(name = "dish", nullable = false, unique = true)
    @NotBlank
    @Size(max = 128)
    private String dish;

    @Column(name = "price", nullable = false, unique = false)
    @NotNull
    private Integer price;
}