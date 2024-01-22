package ru.javaops.bootjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.to.VoteTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class VotesUtil {
    private final LocalTime MAX_VOTE_TIME = LocalTime.of(11, 0);

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getRestaurant().getId(), vote.getRegistered(), vote.getRegisteredTime());
    }

    public static boolean checkVoteTime(Vote vote) {
        return Objects.equals(vote.getRegistered(), LocalDate.now()) && vote.getRegisteredTime().isBefore(MAX_VOTE_TIME);
    }

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream()
                .map(VotesUtil::createTo)
                .collect(Collectors.toList());
    }
}