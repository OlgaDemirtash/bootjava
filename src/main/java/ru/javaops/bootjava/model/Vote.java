package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "vote")
@Getter
@Setter
public class Vote {

    @Column(name = "voteid", nullable = false, updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @EmbeddedId
    private VoteId voteId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;
}