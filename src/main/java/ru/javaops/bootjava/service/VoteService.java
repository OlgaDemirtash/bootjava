package ru.javaops.bootjava.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoteRepository;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    @Transactional
    public Vote save(Vote vote) {
        //restaurant.setUser(userRepository.getExisted(userId));
        return voteRepository.save(vote);
    }
}