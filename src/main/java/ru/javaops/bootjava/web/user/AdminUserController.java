package ru.javaops.bootjava.web.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.User;

import java.net.URI;
import java.util.List;

import static ru.javaops.bootjava.web.RestValidation.assureIdConsistent;
import static ru.javaops.bootjava.web.RestValidation.checkNew;

@RestController
@RequestMapping(value = AdminUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
// TODO: cache only most requested, seldom changed data!
@Tag(name = "AdminUserController", description = "Controller for administrate users")
public class AdminUserController extends AbstractUserController {

    static final String REST_URL = "/api/admin/users";

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Provide user ID for get details")
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "users", key = "#id")
    @Operation(summary = "Delete user by ID", description = "Provide user ID for deletion")
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "User list will be provided")
    public List<User> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create user", description = "Provide user details")
    @CachePut(value = "users", key = "#result.body.id")
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        User created = repository.prepareAndSave(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CachePut(value = "users", key = "#id")
    @Operation(summary = "Update user", description = "Provide user details,user ID")
    public User update(@Valid @RequestBody User user, @PathVariable int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        return repository.prepareAndSave(user);
    }

    @GetMapping("/by-email")
    @Operation(summary = "Get user by email", description = "Provide user email")
    public User getByEmail(@RequestParam String email) {
        log.info("getByEmail {}", email);
        return repository.getExistedByEmail(email);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CachePut(value = "users", key = "#id")
    @Operation(summary = "Enable user", description = "Provide user ID and enable status (true/false)")
    public User enable(@PathVariable int id, @RequestParam boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        User user = repository.getExisted(id);
        user.setEnabled(enabled);
        return user;
    }
}