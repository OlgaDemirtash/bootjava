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
import ru.javaops.bootjava.error.NotFoundException;
import ru.javaops.bootjava.model.Menu;
import ru.javaops.bootjava.repository.MenuRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.service.MenuService;
import ru.javaops.bootjava.validation.ValidationUtil;
import ru.javaops.bootjava.web.AuthUser;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import static ru.javaops.bootjava.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "AdminMenuController", description = "Controller for edit restaurant menu")
public class AdminMenuController {
    static final String REST_URL = "/api/admin/menu";
    private final MenuRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final MenuService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get menu position", description = "Provide an ID to get menu position details")
    public Menu getMenuPosition(@PathVariable int id) {
        log.info("get menu position {} ", id);
        Optional<Menu> menuPosition = Optional.ofNullable(repository.getExisted(id));
        return menuPosition.orElseThrow(() -> new NotFoundException("Position with id=" + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete menu position", description = "Provide ID position to delete")
    public void delete(@PathVariable int id) {
        log.info("delete position from menu {}", id);
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create menu position", description = "Provide menu details and restaurant ID to create")
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody Menu menu) {
        log.info("create menu position {} ", menu);
        ValidationUtil.checkNew(menu);
        menu.setRegistered(LocalDate.now());
        menu.setRestaurant(restaurantRepository.getExisted(menu.getRestaurant().getId()));
        Menu created = repository.save(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update menu position", description = "Provide menu details, menu position ID")
    public void update(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Menu menu, @PathVariable int id) {
        int userId = authUser.id();
        log.info("update menu position ID {} by user {}", id, userId);
        assureIdConsistent(menu, id);
        service.update(menu);
    }
}