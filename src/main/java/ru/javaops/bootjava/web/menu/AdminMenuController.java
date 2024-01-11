package ru.javaops.bootjava.web.menu;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.error.NotFoundException;
import ru.javaops.bootjava.model.Menu;
import ru.javaops.bootjava.repository.MenuRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.service.MenuService;
import ru.javaops.bootjava.validation.ValidationUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminMenuController {
    static final String REST_URL = "/api/admin/restaurants/";
    private final MenuRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final MenuService service;

    @GetMapping("/{id}/menu")
    public List<Menu> getCurrentMenu(@PathVariable int id) {
        log.info("get menu for today");
        return service.getAllByDate(LocalDate.now(), id);
    }

    @GetMapping("/{id}/menu/{position}")
    public Menu getPosition(@PathVariable int id, @PathVariable int position) {
        log.info("get menu position {} ", position);
        Optional<Menu> menuPosition = Optional.ofNullable(repository.getExisted(position));
        return menuPosition.orElseThrow(() -> new NotFoundException("Position with id=" + id + " not found"));
    }

    @DeleteMapping("/{id}/menu/{position}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @Valid @PathVariable int position) {
        log.info("delete position from menu {}", position);
        repository.deleteExisted(position);
    }

    @PostMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody Menu menu, @PathVariable int id) {
        log.info("create menu position {} ", menu);
        ValidationUtil.checkNew(menu);
        menu.setRegistered(LocalDate.now());
        menu.setRestaurant(restaurantRepository.getReferenceById(id));
        Menu created = repository.save(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{position}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}