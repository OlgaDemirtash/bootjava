package ru.javaops.bootjava.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.to.VoteTo;
import ru.javaops.bootjava.util.JsonUtil;
import ru.javaops.bootjava.util.VotesUtil;
import ru.javaops.bootjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.restaurant1;
import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.restaurant3;
import static ru.javaops.bootjava.web.user.UserTestData.USER_MAIL;
import static ru.javaops.bootjava.web.user.UserTestData.user;
import static ru.javaops.bootjava.web.vote.VoteController.REST_URL;
import static ru.javaops.bootjava.web.vote.VoteTestData.*;

public class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        Vote updatedVote = vote1ForUser1;
        updatedVote.setRestaurant(restaurant3);
        VoteTo updated = VotesUtil.createTo(updatedVote);
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
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
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
    @Transactional(propagation = Propagation.NEVER)
    void createDuplicate() throws Exception {
        VoteTo invalid = VotesUtil.createTo(getNew(user, restaurant1));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}