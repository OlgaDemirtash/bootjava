package ru.javaops.bootjava.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.bootjava.error.DataConflictException;
import ru.javaops.bootjava.error.NotFoundException;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.MenuRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.to.VoteTo;
import ru.javaops.bootjava.util.VotesUtil;
import ru.javaops.bootjava.validation.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository repository;

    private final MenuRepository menuRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    public Vote getForUser(int id, int userId) {
        return repository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException("Vote with " + id + " not found for user" + userId));
    }

    @Transactional
    public void update(VoteTo voteTo, int userId) {
        int voteId = voteTo.getId();
        Vote vote = getForUser(voteId, userId);
        ValidationUtil.checkNotFoundWithId(vote, voteId);
        int restaurantId = voteTo.getRestaurantId();
        vote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        vote.setRegisteredTime(LocalTime.now());
        if (!VotesUtil.checkVoteTime(vote)) {
            throw new DataConflictException("Voting time has already passed for user" + userId + "and restaurant" + restaurantId);
        }
        if (!menuRepository.existsByRestaurantIdAndRegistered(restaurantId, LocalDate.now())) {
            throw new DataConflictException("There is no menu for the restaurant " + restaurantId + "on the current date.");
        }
        repository.save(vote);
    }

    @Transactional
    public Vote create(VoteTo voteTo, int userId) {
        Assert.notNull(voteTo, "vote must not be null");
        ValidationUtil.checkNew(voteTo);
        int restaurantId = voteTo.getRestaurantId();
        Vote vote = new Vote(null, LocalDate.now(), userRepository.getReferenceById(userId), restaurantRepository.getReferenceById(restaurantId));
        if (!menuRepository.existsByRestaurantIdAndRegistered(restaurantId, LocalDate.now())) {
            throw new DataConflictException("There is no menu for the restaurant " + restaurantId + " on the current date.");
        }
        return repository.save(vote);
    }
}