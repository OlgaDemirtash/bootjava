package ru.javaops.bootjava.web.vote;

import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.to.VoteTo;
import ru.javaops.bootjava.web.MatcherFactory;

import java.time.LocalDate;

import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.*;
import static ru.javaops.bootjava.web.user.UserTestData.user;
import static ru.javaops.bootjava.web.user.UserTestData.user2;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant.menuItems", "restaurant.votes", "registeredTime");
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VoteTo.class, "restaurant.menuItems", "restaurant.votes", "registeredTime");

    public static final int VOTE1_USER1_ID = 100030;
    public static final int VOTE1_USER2_ID = 100031;
    public static final LocalDate VOTE1_USER1_DATE = LocalDate.now();
    public static final LocalDate VOTE1_USER2_DATE = LocalDate.now();

    public static final Vote vote1ForUser1 = new Vote(VOTE1_USER1_ID, VOTE1_USER1_DATE, user, restaurant1);
    public static final Vote vote1ForUser2 = new Vote(VOTE1_USER2_ID, VOTE1_USER2_DATE, user2, restaurant2);

    public static Vote getNew(User user, Restaurant restaurant) {
        return new Vote(null, LocalDate.now(), user, restaurant);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1_USER1_ID, VOTE1_USER1_DATE, user, restaurant3);
    }
}