package ru.javaops.bootjava.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.to.VoteTo;
import ru.javaops.bootjava.util.JsonUtil;
import ru.javaops.bootjava.util.VotesUtil;
import ru.javaops.bootjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.restaurant3;
import static ru.javaops.bootjava.web.user.UserTestData.USER_MAIL;
import static ru.javaops.bootjava.web.vote.VoteController.REST_URL;
import static ru.javaops.bootjava.web.vote.VoteTestData.*;

@ActiveProfiles("error")
public class VoteControllerErrorTimeTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateAfterMaxTime() throws Exception {
        Vote updatedVote = vote1ForUser1;
        updatedVote.setRestaurant(restaurant3);
        VoteTo updated = VotesUtil.createTo(updatedVote);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE1_USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isConflict());

        VOTE_TO_MATCHER.assertMatch(VotesUtil.createTo(voteRepository.getExisted(VOTE1_USER1_ID)), updated);
    }
}