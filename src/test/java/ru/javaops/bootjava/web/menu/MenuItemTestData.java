package ru.javaops.bootjava.web.menu;

import ru.javaops.bootjava.model.MenuItem;
import ru.javaops.bootjava.to.MenuItemTo;
import ru.javaops.bootjava.web.MatcherFactory;

import java.time.LocalDate;

import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.*;

public class MenuItemTestData {

    public static final MatcherFactory.Matcher<MenuItem> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "restaurant.votes", "restaurant.menuItems");
    public static final MatcherFactory.Matcher<MenuItemTo> MENU_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItemTo.class);
    public static final int NOT_FOUND = 100;

    public static final int MENU_1_RESTAURANT_ID = 100005;

    public static final Integer MENU_1_ITEM_1_ID = 100017;
    public static final Integer MENU_1_ITEM_2_ID = 100018;
    public static final Integer MENU_1_ITEM_3_ID = 100019;

    public static final String MENU_1_ITEM_1_NAME = "Том-ям";
    public static final String MENU_1_ITEM_2_NAME = "Роллы с авокадо";
    public static final String MENU_1_ITEM_3_NAME = "Чай с жасмином";

    public static final int MENU_1_ITEM_1_PRICE = 600;
    public static final int MENU_1_ITEM_2_PRICE = 400;
    public static final int MENU_1_ITEM_3_PRICE = 200;

    public static final int MENU_2_RESTAURANT_ID = 100006;

    public static final Integer MENU_2_ITEM_1_ID = 100020;
    public static final Integer MENU_2_ITEM_2_ID = 100021;
    public static final Integer MENU_2_ITEM_3_ID = 100022;

    public static final String MENU_2_ITEM_1_NAME = "Сибас на гриле";
    public static final String MENU_2_ITEM_2_NAME = "Овощи на гриле";
    public static final String MENU_2_ITEM_3_NAME = "Капучино";

    public static final int MENU_2_ITEM_1_PRICE = 800;
    public static final int MENU_2_ITEM_2_PRICE = 300;
    public static final int MENU_2_ITEM_3_PRICE = 220;

    public static final int MENU_3_RESTAURANT_ID = 100007;

    public static final Integer MENU_3_ITEM_1_ID = 100023;
    public static final Integer MENU_3_ITEM_2_ID = 100024;
    public static final Integer MENU_3_ITEM_3_ID = 100025;

    public static final String MENU_3_ITEM_1_NAME = "Шоколадный мусс";
    public static final String MENU_3_ITEM_2_NAME = "Торт Наполеон";
    public static final String MENU_3_ITEM_3_NAME = "Молочный коктейль";

    public static final int MENU_3_ITEM_1_PRICE = 400;
    public static final int MENU_3_ITEM_2_PRICE = 350;
    public static final int MENU_3_ITEM_3_PRICE = 150;

    public static final int MENU_4_RESTAURANT_ID = 100005;

    public static final Integer MENU_4_ITEM_1_ID = 100008;
    public static final Integer MENU_4_ITEM_2_ID = 100009;
    public static final Integer MENU_4_ITEM_3_ID = 100010;
    public static final String MENU_4_ITEM_1_NAME = "Том-ям";
    public static final String MENU_4_ITEM_2_NAME = "Роллы с авокадо";
    public static final String MENU_4_ITEM_3_NAME = "Чай с жасмином";
    public static final int MENU_4_ITEM_1_PRICE = 600;
    public static final int MENU_4_ITEM_2_PRICE = 400;
    public static final int MENU_4_ITEM_3_PRICE = 200;


    public static final LocalDate MENU_4_DATE = LocalDate.of(2024, 1, 7);
    public static final LocalDate MENU_5_DATE = LocalDate.of(2024, 1, 8);
    public static final LocalDate MENU_6_DATE = LocalDate.of(2024, 1, 9);

    public static final MenuItem MENU_1_ITEM_1 = new MenuItem(MENU_1_ITEM_1_ID, MENU_1_ITEM_1_NAME, LocalDate.now(), null, MENU_1_ITEM_1_PRICE);
    public static final MenuItem MENU_1_ITEM_2 = new MenuItem(MENU_1_ITEM_2_ID, MENU_1_ITEM_2_NAME, LocalDate.now(), null, MENU_1_ITEM_2_PRICE);
    public static final MenuItem MENU_1_ITEM_3 = new MenuItem(MENU_1_ITEM_3_ID, MENU_1_ITEM_3_NAME, LocalDate.now(), null, MENU_1_ITEM_3_PRICE);

    public static final MenuItem MENU_2_ITEM_1 = new MenuItem(MENU_2_ITEM_1_ID, MENU_2_ITEM_1_NAME, LocalDate.now(), restaurant2, MENU_2_ITEM_1_PRICE);
    public static final MenuItem MENU_2_ITEM_2 = new MenuItem(MENU_2_ITEM_2_ID, MENU_2_ITEM_2_NAME, LocalDate.now(), restaurant2, MENU_2_ITEM_2_PRICE);
    public static final MenuItem MENU_2_ITEM_3 = new MenuItem(MENU_2_ITEM_3_ID, MENU_2_ITEM_3_NAME, LocalDate.now(), restaurant2, MENU_2_ITEM_3_PRICE);

    public static final MenuItem MENU_3_ITEM_1 = new MenuItem(MENU_3_ITEM_1_ID, MENU_3_ITEM_1_NAME, LocalDate.now(), restaurant3, MENU_3_ITEM_1_PRICE);
    public static final MenuItem MENU_3_ITEM_2 = new MenuItem(MENU_3_ITEM_2_ID, MENU_3_ITEM_2_NAME, LocalDate.now(), restaurant3, MENU_3_ITEM_2_PRICE);
    public static final MenuItem MENU_3_ITEM_3 = new MenuItem(MENU_3_ITEM_3_ID, MENU_3_ITEM_3_NAME, LocalDate.now(), restaurant3, MENU_3_ITEM_3_PRICE);


    public static final MenuItem MENU_4_ITEM_1 = new MenuItem(MENU_4_ITEM_1_ID, MENU_4_ITEM_1_NAME, MENU_4_DATE, null, MENU_4_ITEM_1_PRICE);
    public static final MenuItem MENU_4_ITEM_2 = new MenuItem(MENU_4_ITEM_2_ID, MENU_4_ITEM_2_NAME, MENU_4_DATE, null, MENU_4_ITEM_2_PRICE);
    public static final MenuItem MENU_4_ITEM_3 = new MenuItem(MENU_4_ITEM_3_ID, MENU_4_ITEM_3_NAME, MENU_4_DATE, null, MENU_4_ITEM_3_PRICE);


    public static MenuItem getNew() {
        return new MenuItem(null, "New menu item name", LocalDate.now(), restaurant1, 600);
    }

    public static MenuItem getUpdated() {
        return new MenuItem(MENU_1_ITEM_1_ID, "UpdatedName", LocalDate.now(), null, 455);
    }
}