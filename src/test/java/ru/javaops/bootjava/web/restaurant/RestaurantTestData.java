package ru.javaops.bootjava.web.restaurant;

import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.web.MatcherFactory;

import java.time.LocalDate;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menus", "votes", "registered");
    public static final int RESTAURANT_ID_1 = 100005;
    public static final int RESTAURANT_ID_2 = 100006;
    public static final int RESTAURANT_ID_3 = 100007;
    public static final int NOT_FOUND = 100;
    public static final String RESTAURANT_NAME_1 = "Koт Шредингера";
    public static final String RESTAURANT_NAME_2 = "Джумбус";
    public static final String RESTAURANT_NAME_3 = "Андерсон";

    public static final LocalDate RESTAURANT_REGISTERED_1 = LocalDate.now();
    public static final LocalDate RESTAURANT_REGISTERED_2 = LocalDate.now();
    public static final LocalDate RESTAURANT_REGISTERED_3 = LocalDate.now();

    public static final String RESTAURANT_DESCRIPTION_1 = "Ресторан японской, китайской кухни";
    public static final String RESTAURANT_DESCRIPTION_2 = "Ресторан средиземноморской кухни";
    public static final String RESTAURANT_DESCRIPTION_3 = "Кафе для родителей с детьми, европейская кухня";

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID_1, RESTAURANT_NAME_1, RESTAURANT_DESCRIPTION_1);
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID_2, RESTAURANT_NAME_2, RESTAURANT_DESCRIPTION_2);
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_ID_3, RESTAURANT_NAME_3, RESTAURANT_DESCRIPTION_3);

    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant name", "new restaurant description");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID_1, "UpdatedName", "updatedDescription");
    }
}