package ru.javaops.bootjava.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.bootjava.model.MenuItem;
import ru.javaops.bootjava.repository.MenuItemRepository;
import ru.javaops.bootjava.service.MenuItemService;

import java.time.LocalDate;
import java.util.List;


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
}