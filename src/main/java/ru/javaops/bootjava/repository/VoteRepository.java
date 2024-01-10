package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.model.VoteId;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, VoteId> {

    List<Vote> findAllByRestaurantIdAndVoteIdRegistered(int restaurantId, Date registered);

    Optional<Vote> findAllByVoteIdUserIdAndVoteIdRegistered(int userId, Date registered);

}