package ru.javaops.bootjava.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.service.RestaurantService;
import ru.javaops.bootjava.web.AuthUser;

import java.net.URI;
import java.util.List;

import static ru.javaops.bootjava.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.bootjava.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "AdminRestaurantController", description = "Controller for edit restaurant")
public class AdminRestaurantController {
    static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantService service;

    private final RestaurantRepository repository;

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by ID", description = "Provide restaurant ID for get details")
    public ResponseEntity<Restaurant> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get restaurant {} for user {}", id, authUser.id());
        return ResponseEntity.ofNullable(repository.getExisted(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CachePut(value = "restaurants", key = "#id")
    @Operation(summary = "Delete restaurant by ID", description = "Provide restaurant ID for deletion")
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("delete restaurant ID {} by user {}", id, authUser.id());
        repository.deleteExisted(id);
    }

    @GetMapping
    @Operation(summary = "Get all restaurants", description = "Restaurant list will be provided")
    public List<Restaurant> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return service.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CachePut(value = "restaurants", key = "#id")
    @Operation(summary = "Update restaurant", description = "Provide restaurant details, restaurant ID")
    public void update(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        int userId = authUser.id();
        log.info("update {} by user {}", restaurant, userId);
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CachePut(value = "restaurants", key = "#result.body.id")
    @Operation(summary = "Create restaurant", description = "Provide restaurant details")
    public ResponseEntity<Restaurant> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Restaurant restaurant) {
        int userId = authUser.id();
        log.info("create {} by user {}", restaurant, userId);
        checkNew(restaurant);
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}