package ru.javaops.bootjava.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.bootjava.CurrentDateTimeProvider;
import ru.javaops.bootjava.error.DataConflictException;
import ru.javaops.bootjava.error.NotFoundException;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.MenuItemRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.to.VoteTo;
import ru.javaops.bootjava.util.VotesUtil;
import ru.javaops.bootjava.validation.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VoteService {
    public final LocalTime MAX_VOTE_TIME = LocalTime.of(11, 0);

    private final VoteRepository repository;

    private final MenuItemRepository menuItemRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    private final CurrentDateTimeProvider currentDateTimeProvider;

    public Vote getForUser(int id, int userId) {
        return repository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException("Vote with " + id + " not found for user" + userId));
    }

    public VoteTo getCurrentVote(int userId) {
        return VotesUtil.createTo(repository.findByUserIdAndRegistered(userId, LocalDate.now()).orElseThrow(() -> new NotFoundException("Vote for  for user" + userId + " for today not found")));
    }

    @Transactional
    public void update(VoteTo voteTo, int userId) {
        int voteId = voteTo.getId();
        Vote vote = getForUser(voteId, userId);
        ValidationUtil.checkNotFoundWithId(vote, voteId);
        int restaurantId = voteTo.getRestaurantId();
        vote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        vote.setRegisteredTime(currentDateTimeProvider.getCurrentTime());
        if (!checkVoteTime(vote)) {
            throw new DataConflictException("Voting time (until 11:00) has already passed for user" + userId + "and restaurant" + restaurantId);
        }
        menuIssuedCheck(restaurantId);
        repository.save(vote);
    }

    @Transactional
    public Vote create(VoteTo voteTo, int userId) {
        Assert.notNull(voteTo, "vote must not be null");
        int restaurantId = voteTo.getRestaurantId();
        Vote vote = new Vote(null, LocalDate.now(), userRepository.getReferenceById(userId), restaurantRepository.getReferenceById(restaurantId));
        menuIssuedCheck(restaurantId);
        return repository.save(vote);
    }

    private void menuIssuedCheck(int restaurantId) {
        if (!menuItemRepository.existsByRestaurantIdAndIssued(restaurantId, LocalDate.now())) {
            throw new DataConflictException("There is no menu for the restaurant " + restaurantId + " on the current date.");
        }
    }

    public boolean checkVoteTime(Vote vote) {
        return Objects.equals(vote.getRegistered(), currentDateTimeProvider.getCurrentDate()) && vote.getRegisteredTime().isBefore(MAX_VOTE_TIME);
    }
}