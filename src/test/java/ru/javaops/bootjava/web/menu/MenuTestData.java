package ru.javaops.bootjava.web.menu;

import ru.javaops.bootjava.model.Menu;
import ru.javaops.bootjava.web.MatcherFactory;

import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.*;

public class MenuTestData {

    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant.votes", "restaurant.menus");
    public static final int NOT_FOUND = 100;
    public static final int MENU_1_RESTAURANT_ID = 100005;
    public static final Integer MENU_1_POSITION_1_ID = 100017;
    public static final Integer MENU_1_POSITION_2_ID = 100018;
    public static final Integer MENU_1_POSITION_3_ID = 100019;
    public static final Integer MENU_2_POSITION_1_ID = 100020;
    public static final Integer MENU_2_POSITION_2_ID = 100021;
    public static final Integer MENU_2_POSITION_3_ID = 100022;
    public static final Integer MENU_3_POSITION_1_ID = 100023;
    public static final Integer MENU_3_POSITION_2_ID = 100024;
    public static final Integer MENU_3_POSITION_3_ID = 100025;

    public static final String MENU_1_POSITION_1_NAME = "Том-ям";
    public static final String MENU_1_POSITION_2_NAME = "Роллы с авокадо";
    public static final String MENU_1_POSITION_3_NAME = "Чай с жасмином";

    public static final int MENU_1_POSITION_1_PRICE = 600;
    public static final int MENU_1_POSITION_2_PRICE = 400;
    public static final int MENU_1_POSITION_3_PRICE = 200;


    public static final int MENU_2_RESTAURANT_ID = 100006;

    public static final String MENU_2_POSITION_1_NAME = "Сибас на гриле";
    public static final String MENU_2_POSITION_2_NAME = "Овощи на гриле";
    public static final String MENU_2_POSITION_3_NAME = "Капучино";

    public static final int MENU_2_POSITION_1_PRICE = 800;
    public static final int MENU_2_POSITION_2_PRICE = 300;
    public static final int MENU_2_POSITION_3_PRICE = 220;

    public static final int MENU_3_RESTAURANT_ID = 100007;

    public static final String MENU_3_POSITION_1_NAME = "Шоколадный мусс";
    public static final String MENU_3_POSITION_2_NAME = "Торт Наполеон";
    public static final String MENU_3_POSITION_3_NAME = "Молочный коктейль";

    public static final int MENU_3_POSITION_1_PRICE = 400;
    public static final int MENU_3_POSITION_2_PRICE = 350;
    public static final int MENU_3_POSITION_3_PRICE = 150;

    public static final Menu menu1_position_1 = new Menu(MENU_1_POSITION_1_ID, MENU_1_POSITION_1_NAME, restaurant1, MENU_1_POSITION_1_PRICE);
    public static final Menu menu1_position_2 = new Menu(MENU_1_POSITION_2_ID, MENU_1_POSITION_2_NAME, restaurant1, MENU_1_POSITION_2_PRICE);
    public static final Menu menu1_position_3 = new Menu(MENU_1_POSITION_3_ID, MENU_1_POSITION_3_NAME, restaurant1, MENU_1_POSITION_3_PRICE);

     public static final Menu menu2_position_1 = new Menu(MENU_2_POSITION_1_ID, MENU_2_POSITION_1_NAME, restaurant2, MENU_2_POSITION_1_PRICE);
    public static final Menu menu2_position_2 = new Menu(MENU_2_POSITION_2_ID, MENU_2_POSITION_2_NAME, restaurant2, MENU_2_POSITION_2_PRICE);
    public static final Menu menu2_position_3 = new Menu(MENU_2_POSITION_3_ID, MENU_2_POSITION_3_NAME, restaurant2, MENU_2_POSITION_3_PRICE);

    public static final Menu menu3_position_1 = new Menu(MENU_3_POSITION_1_ID, MENU_3_POSITION_1_NAME, restaurant3, MENU_3_POSITION_1_PRICE);
    public static final Menu menu3_position_2 = new Menu(MENU_3_POSITION_2_ID, MENU_3_POSITION_2_NAME, restaurant3, MENU_3_POSITION_2_PRICE);
    public static final Menu menu3_position_3 = new Menu(MENU_3_POSITION_3_ID, MENU_3_POSITION_3_NAME, restaurant3, MENU_3_POSITION_3_PRICE);


    public static Menu getNew() {
        return new Menu(null, "New menu position name", restaurant1, 600);
    }

    public static Menu getUpdated() {
        return new Menu(MENU_1_POSITION_1_ID, "UpdatedName", restaurant1, 455);
    }
}