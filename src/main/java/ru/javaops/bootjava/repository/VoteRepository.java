package ru.javaops.bootjava.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    List<Vote> findAllByRestaurantIdAndRegistered(int restaurantId, LocalDate registered);

    Optional<Vote> findAllByUserIdAndRegistered(int userId, LocalDate registered);

    Optional<Vote> findByIdAndUserId(int id, int userId);
}