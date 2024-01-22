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
    static final String REST_MENU_ITEMS = "/with-menu-items";
    private final RestaurantService service;

    private final RestaurantRepository repository;

    @GetMapping
    @Operation(summary = "Get all restaurants", description = "Restaurant list will be provided")
    public List<Restaurant> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return service.getAll();
    }

    @GetMapping(REST_MENU_ITEMS)
    @Operation(summary = "Get all restaurants with current menu", description = "Restaurant list with menu will be provided")
    public List<Restaurant> getAllWithCurrentMenuItems(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAllWithMenu for user {}", authUser.id());
        return service.getAllWithMenuItemsByDate(LocalDate.now());
    }
}