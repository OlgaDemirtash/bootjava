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
import java.time.LocalTime;

@Entity
@Table(name = "vote", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"registered", "user_id"})})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedEntityGraph(name = "graph.voteWithRestaurant", attributeNodes = @NamedAttributeNode("restaurant"))
public class Vote extends BaseEntity {

    @Column(name = "registered", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate registered = LocalDate.now();

    @Column(name = "registered_time", nullable = false, columnDefinition = "TIME DEFAULT CURRENT_TIME", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalTime registeredTime = LocalTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "INTEGER", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", columnDefinition = "INTEGER", nullable = false)
    @JsonIgnoreProperties({"votes", "menuItems"})
    private Restaurant restaurant;

    public Vote(Integer id, @NotNull LocalDate registered, User user, Restaurant restaurant) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.registered = registered;
    }
}