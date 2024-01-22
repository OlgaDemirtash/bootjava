package ru.javaops.bootjava.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.web.AbstractControllerTest;

import static ru.javaops.bootjava.web.vote.VoteController.REST_URL;

@ActiveProfiles({"error"})
public class VoteControllerErrorTimeTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

//    @Test
//    @WithUserDetails(value = USER_MAIL)
//    void updateBeforeMaxTime() throws Exception {
//        Vote updatedVote = vote1ForUser1;
//        updatedVote.setRestaurant(restaurant3);
//        VoteTo updated = VotesUtil.createTo(updatedVote);
//        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE1_USER1_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(updated)))
//                .andExpect(status().isConflict());
//
//        VOTE_TO_MATCHER.assertMatch(VotesUtil.createTo(voteRepository.getExisted(VOTE1_USER1_ID)), updated);
//    }
}