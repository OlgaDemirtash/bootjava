package ru.javaops.bootjava.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.to.VoteTo;
import ru.javaops.bootjava.util.JsonUtil;
import ru.javaops.bootjava.util.VotesUtil;
import ru.javaops.bootjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.restaurant1;
import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.restaurant3;
import static ru.javaops.bootjava.web.user.UserTestData.USER_MAIL;
import static ru.javaops.bootjava.web.user.UserTestData.user;
import static ru.javaops.bootjava.web.vote.VoteTestData.*;

class ProfileVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = VoteController.REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        VoteTo updated = VotesUtil.createTo(vote1ForUser1);
        updated.setRestaurantId(restaurant3.getId());
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE1_USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        VOTE_TO_MATCHER.assertMatch(VotesUtil.createTo(voteRepository.getExisted(VOTE1_USER1_ID)), updated);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void create() throws Exception {
        VoteTo newVoteTo = VotesUtil.createTo(getNew(user, restaurant1));
        ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoteTo)));

        VoteTo created = VOTE_TO_MATCHER.readFromJson(action);
        int newId = created.getId();
        newVoteTo.setId(newId);
        VOTE_TO_MATCHER.assertMatch(VotesUtil.createTo(voteRepository.getExisted(newId)), newVoteTo);
        //VOTE_MATCHER.assertMatch(voteRepository.getExisted(newId), newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createInvalid() throws Exception {
        VoteTo newVoteTo = VotesUtil.createTo(getNew(user, restaurant1));
        ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoteTo)));

        VoteTo created = VOTE_TO_MATCHER.readFromJson(action);
        int newId = created.getId();
        newVoteTo.setId(newId);
        VOTE_TO_MATCHER.assertMatch(created, newVoteTo);
    }
}