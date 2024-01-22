package ru.javaops.bootjava.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.MenuItem;
import ru.javaops.bootjava.repository.MenuItemRepository;
import ru.javaops.bootjava.service.MenuItemService;
import ru.javaops.bootjava.web.AuthUser;

import java.time.LocalDate;
import java.util.List;

import static ru.javaops.bootjava.util.DateTimeUtil.dayOrMax;
import static ru.javaops.bootjava.util.DateTimeUtil.dayOrMin;


@RestController
@RequestMapping(value = MenuItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "MenuItemController", description = "Controller for view restaurant menu items")
public class MenuItemController {

    static final String REST_URL = "/api/restaurants";
    static final String REST_URL_MENU = "/menu-items";

    private final MenuItemService service;
    private final MenuItemRepository repository;

    @GetMapping("/{id}" + REST_URL_MENU)
    @Operation(summary = "Get current menu items for the restaurant ID", description = "Provide an restaurant ID to get restaurant menu item details")
    public List<MenuItem> getCurrentMenu(@PathVariable int id) {
        log.info("get menu items for today");
        return service.getAllByDate(LocalDate.now(), id);
    }

    @GetMapping("/{id}" + REST_URL_MENU + "-all")
    @Operation(summary = "Get all menu items for the restaurant ID", description = "Provide an restaurant ID to get restaurant menu item details")
    public List<MenuItem> getAllMenu(@PathVariable int id) {
        log.info("get all menu items");
        return service.getAll(id);
    }

    @GetMapping("/filter")
    public List<MenuItem> getBetween(@AuthenticationPrincipal AuthUser authUser,
                                     @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                     @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        int userId = authUser.id();
        log.info("getBetween dates({} - {}) for user {}", startDate, endDate, userId);
        return repository.findAllByIssuedGreaterThanEqualAndIssuedLessThanEqual(dayOrMin(startDate), dayOrMax(endDate));
    }
}