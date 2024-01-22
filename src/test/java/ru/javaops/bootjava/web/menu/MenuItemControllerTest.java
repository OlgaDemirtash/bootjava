package ru.javaops.bootjava.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.bootjava.web.menu.MenuItemController.REST_URL;
import static ru.javaops.bootjava.web.menu.MenuItemController.REST_URL_MENU;
import static ru.javaops.bootjava.web.menu.MenuItemTestData.*;
import static ru.javaops.bootjava.web.user.UserTestData.USER_MAIL;

public class MenuItemControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getMenuItems() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_1_RESTAURANT_ID + REST_URL_MENU))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MENU_1_ITEM_1, MENU_1_ITEM_2, MENU_1_ITEM_3));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_1_RESTAURANT_ID))
                .andExpect(status().isUnauthorized());
    }
}