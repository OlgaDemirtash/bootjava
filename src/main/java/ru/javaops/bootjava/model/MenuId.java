package ru.javaops.bootjava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode
@Setter
@Getter
@Embeddable
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MenuId implements Serializable {

    private Date registered = new Date();

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;


    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQUENCE")
    @SequenceGenerator(name = "GLOBAL_SEQUENCE", sequenceName = "GLOBAL_SEQUENCE_1", allocationSize = 1, initialValue = 1)
    @Column(name = "dish_position", unique = true, nullable = false, columnDefinition = "integer default nextval('GLOBAL_SEQUENCE_1')")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // https://stackoverflow.com/a/28025008/548473
    private Integer dish_position;

}