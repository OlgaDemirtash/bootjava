package ru.javaops.bootjava.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.MenuItem;
import ru.javaops.bootjava.repository.MenuItemRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.service.MenuItemService;
import ru.javaops.bootjava.to.MenuItemTo;
import ru.javaops.bootjava.validation.ValidationUtil;
import ru.javaops.bootjava.web.AuthUser;

import java.net.URI;

import static ru.javaops.bootjava.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminMenuItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "AdminMenuItemController", description = "Controller for edit restaurant menu")
public class AdminMenuItemController {
    static final String REST_URL = "/api/admin/menu-items";
    private final MenuItemRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get menu item", description = "Provide an ID to get menu item details")
    public MenuItem getMenuItem(@PathVariable int id) {
        log.info("get menu item {} ", id);
        return repository.getExisted(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete menu item", description = "Provide menu item ID to delete")
    public void delete(@PathVariable int id) {
        log.info("delete menu item {}", id);
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create menu item", description = "Provide menu item details and restaurant ID to create")
    public ResponseEntity<MenuItem> createWithLocation(@Valid @RequestBody MenuItemTo menuItemTo) {
        log.info("create menu item {} ", menuItemTo);
        ValidationUtil.checkNew(menuItemTo);
        MenuItem created = service.create(menuItemTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update menu item", description = "Provide menu details, menu item ID")
    public void update(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody MenuItem menuItem, @PathVariable int id) {
        int userId = authUser.id();
        log.info("update menu item ID {} by user {}", id, userId);
        assureIdConsistent(menuItem, id);
        repository.prepareAndSave(menuItem);
    }
}