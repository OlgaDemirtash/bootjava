package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javaops.bootjava.validation.NoHtml;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "restaurant",  uniqueConstraints = @UniqueConstraint(name = "unique_restaurant_name", columnNames = {"name"}))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedEntityGraph(name = "graph.RestaurantWithMenus", attributeNodes = @NamedAttributeNode("menus"))
@NamedEntityGraph(name = "graph.RestaurantWithVotes", attributeNodes = @NamedAttributeNode("votes"))

@ToString(exclude = {"menus","votes"})
public class Restaurant extends NamedEntity {

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(max = 256)
    @NoHtml
    private String description;

    @Column(name = "registered", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate registered = LocalDate.now();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("registered DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    private List<Menu> menus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("registered DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    private List<Vote> votes;

    public Restaurant(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }
}