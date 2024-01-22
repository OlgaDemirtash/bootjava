package ru.javaops.bootjava.web;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ru.javaops.bootjava.web.menu.AdminMenuItemControllerTest;
import ru.javaops.bootjava.web.menu.MenuItemControllerTest;
import ru.javaops.bootjava.web.restaurant.AdminRestaurantControllerTest;
import ru.javaops.bootjava.web.restaurant.RestaurantControllerTest;
import ru.javaops.bootjava.web.user.AdminUserControllerTest;
import ru.javaops.bootjava.web.user.ProfileControllerTest;
import ru.javaops.bootjava.web.vote.VoteControllerTest;

@Suite
@SelectClasses({VoteControllerTest.class, AdminMenuItemControllerTest.class, MenuItemControllerTest.class, RestaurantControllerTest.class, AdminRestaurantControllerTest.class, ProfileControllerTest.class, AdminUserControllerTest.class})
public class TestAll {
}