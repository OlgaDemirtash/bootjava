package ru.javaops.bootjava.web.vote;

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
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.service.VoteService;
import ru.javaops.bootjava.to.VoteTo;
import ru.javaops.bootjava.util.VotesUtil;
import ru.javaops.bootjava.web.AuthUser;

import java.net.URI;
import java.util.List;

import static ru.javaops.bootjava.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.bootjava.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "VoteController", description = "Controller for restaurant voting")

public class VoteController {
    static final String REST_URL = "/api/profile/votes";
    private final VoteRepository repository;
    private final VoteService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Vote for the restaurant", description = "Provide vote details for voting (restaurant_id).New user vote will be created.")
    public ResponseEntity<VoteTo> createWithLocation(@Valid @RequestBody VoteTo voteTo, @AuthenticationPrincipal AuthUser authUser) {
        log.info("create vote {} for user {}", voteTo, authUser);
        checkNew(voteTo);
        Vote created = service.create(voteTo, authUser.id());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(VotesUtil.createTo(created));
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update user vote for the restaurant", description = "Provide vote details for voting (new restaurant_id), provide vote ID for update vote data.New user vote will be created.")
    public void update(@Valid @RequestBody VoteTo voteTo, @PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        log.info("update {} with id={}", voteTo, id);
        assureIdConsistent(voteTo, id);
        service.update(voteTo, authUser.id());
    }

    @GetMapping("history")
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get all votes for user {}", authUser.id());
        return VotesUtil.getTos(repository.findAllWithRestaurantByUserId(authUser.id()));
    }

    @GetMapping
    public VoteTo get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get current vote for user {}", authUser.id());
        return service.getCurrentVote(authUser.id());
    }
}