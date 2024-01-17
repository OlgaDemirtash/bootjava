package ru.javaops.bootjava.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.service.RestaurantService;
import ru.javaops.bootjava.web.AuthUser;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "RestaurantController", description = "Controller for view restaurants")
public class RestaurantController {
    static final String REST_URL = "/api/profile/restaurants";

    private final RestaurantService service;

    private final RestaurantRepository repository;
    @GetMapping
    @Operation(summary = "Get all restaurants", description = "Restaurant list will be provided")
    public List<Restaurant> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return service.getAll();
    }

    @GetMapping("/all-menus")
    @Operation(summary = "Get all restaurants with menu", description = "Restaurant list with menu will be provided")
    public List<Restaurant> getAllWithMenu(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAllWithMenu for user {}", authUser.id());
        return service.getAllWithMenu();
    }

    @GetMapping("/menus")
    @Operation(summary = "Get all restaurants with menu", description = "Restaurant list with menu will be provided")
    public List<Restaurant> getAllWithMenuByDate(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAllWithMenu for user {}", authUser.id());
        return service.getAllWithMenuByDate(LocalDate.now());
    }

    @GetMapping("/votes")
    @Operation(summary = "Get all restaurants with votes", description = "Restaurant list with votes will be provided")
    public List<Restaurant> getAllWithVotes(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAllWithVotes for user {}", authUser.id());
        return service.getAllWithVotes();
    }
}