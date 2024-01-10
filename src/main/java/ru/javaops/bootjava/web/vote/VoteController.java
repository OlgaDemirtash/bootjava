package ru.javaops.bootjava.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/profile/votes";
//
//    private final MenuRepository repository;
//    private final MenuService service;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Menu> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
//        log.info("get meal {} for user {}", id, authUser.id());
//        return ResponseEntity.of(repository.get(authUser.id(), id));
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
//        log.info("delete {} for user {}", id, authUser.id());
//        Menu restaurant = repository.getBelonged(authUser.id(), id);
//        repository.delete(restaurant);
//    }
//
//    @GetMapping
//    public List<MealTo> getAll(@AuthenticationPrincipal AuthUser authUser) {
//        log.info("getAll for user {}", authUser.id());
//        return MealsUtil.getTos(repository.getAll(authUser.id()), authUser.getUser().getCaloriesPerDay());
//    }
//
//
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Meal meal, @PathVariable int id) {
//        int userId = authUser.id();
//        log.info("update {} for user {}", meal, userId);
//        assureIdConsistent(meal, id);
//        repository.getBelonged(userId, id);
//        service.save(userId, meal);
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Meal> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Meal meal) {
//        int userId = authUser.id();
//        log.info("create {} for user {}", meal, userId);
//        checkNew(meal);
//        Meal created = service.save(userId, meal);
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{id}")
//                .buildAndExpand(created.getId()).toUri();
//        return ResponseEntity.created(uriOfNewResource).body(created);
//    }
//
//
//    @GetMapping("/filter")
//    public List<MealTo> getBetween(@AuthenticationPrincipal AuthUser authUser,
//                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
//                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
//                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
//
//        int userId = authUser.id();
//        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);
//        List<Meal> mealsDateFiltered = repository.getBetweenHalfOpen(userId, atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate));
//        return MealsUtil.getFilteredTos(mealsDateFiltered, authUser.getUser().getCaloriesPerDay(), startTime, endTime);
}