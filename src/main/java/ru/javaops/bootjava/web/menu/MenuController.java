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
import ru.javaops.bootjava.model.Menu;
import ru.javaops.bootjava.repository.MenuRepository;
import ru.javaops.bootjava.service.MenuService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "MenuController", description = "Controller for view restaurant menu")
public class MenuController {

    static final String REST_URL = "/api/restaurants/";
    static final String REST_URL_MENU = "/menu";

    private final MenuService service;

    @GetMapping("/{id}/menu")
    @Operation(summary = "Get current menu for the restaurant ID", description = "Provide an restaurant ID to get restaurant menu details")
    public List<Menu> getCurrentMenu(@PathVariable int id) {
        log.info("get menu for today");
        return service.getAllByDate(LocalDate.now(), id);
    }
}