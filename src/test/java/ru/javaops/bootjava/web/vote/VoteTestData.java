package ru.javaops.bootjava.web.vote;

import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.web.MatcherFactory;
import ru.javaops.bootjava.web.restaurant.RestaurantTestData;

import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.*;

import static ru.javaops.bootjava.web.user.UserTestData.*;


import java.time.LocalDate;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class);


    public static final int VOTE1_USER1_ID = 100026;
    public static final int VOTE1_USER2_ID = 100027;
    public static final int VOTE1_USER1_RESTAURANT = 100005;
    public static final int VOTE1_USER2_RESTAURANT = 100006;

    public static final LocalDate VOTE1_USER1_DATE = LocalDate.of(2024,1,7);
    public static final LocalDate VOTE1_USER2_DATE = LocalDate.of(2024,1,7);
    public static final int VOTE2_USER1_ID = 100028;
    public static final int VOTE2_USER2_ID = 100029;
    public static final int VOTE2_USER1_RESTAURANT = 100007;
    public static final int VOTE2_USER2_RESTAURANT = 100005;
    public static final LocalDate VOTE2_USER1_DATE = LocalDate.of(2024,1,8);
    public static final LocalDate VOTE2_USER2_DATE = LocalDate.of(2024,1,8);


    public static final Vote vote1ForUser1 = new Vote(VOTE1_USER1_ID, VOTE1_USER1_DATE, user , restaurant1);
    public static final Vote vote1ForUser2 = new Vote(VOTE1_USER2_ID, VOTE1_USER2_DATE, user2 , restaurant2   );
    public static final Vote voteForUser1_2 = new Vote(VOTE2_USER1_ID, VOTE2_USER1_DATE, user , restaurant3   );
    public static final Vote voteForUser2_2 = new Vote(VOTE2_USER2_ID, VOTE2_USER2_DATE, user2 , restaurant1   );

    public static Vote getNew(User user, Restaurant restaurant) {
        return new Vote(null, LocalDate.now(), user, restaurant);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1_USER1_ID, VOTE1_USER1_DATE, user, restaurant3);
    }
}