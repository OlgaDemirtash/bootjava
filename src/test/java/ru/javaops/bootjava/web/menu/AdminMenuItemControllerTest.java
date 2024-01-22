package ru.javaops.bootjava.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.model.MenuItem;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.MenuItemRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.service.MenuItemService;
import ru.javaops.bootjava.to.MenuItemTo;
import ru.javaops.bootjava.util.JsonUtil;
import ru.javaops.bootjava.util.MenuItemUtil;
import ru.javaops.bootjava.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.bootjava.web.menu.AdminMenuItemController.REST_URL;
import static ru.javaops.bootjava.web.menu.MenuItemTestData.*;
import static ru.javaops.bootjava.web.restaurant.RestaurantTestData.restaurant1;
import static ru.javaops.bootjava.web.user.UserTestData.ADMIN_MAIL;

public class AdminMenuItemControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Autowired
    private MenuItemService menuItemService;


    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_1_RESTAURANT_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MenuItemTestData.NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MENU_1_ITEM_1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(MENU_1_ITEM_1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + MENU_1_ITEM_1_ID))
                .andExpect(status().isNoContent());
        assertFalse(menuItemRepository.findById(MENU_1_ITEM_1_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        MenuItem updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_1_ITEM_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        MENU_MATCHER.assertMatch(menuItemRepository.getExisted(MENU_1_ITEM_1_ID), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        MenuItemTo newMenuItemTo = MenuItemUtil.createTo(getNew());
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenuItemTo)));

        MenuItem created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        MenuItemTo createdTo = new MenuItemTo(created.getId(), created.getName(), created.getIssued(), newMenuItemTo.getRestaurantId(), created.getPrice());
        MENU_TO_MATCHER.assertMatch(MenuItemUtil.createTo(menuItemService.getByIdWithRestaurant(newId)), createdTo);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        MenuItem invalid = new MenuItem(null, null, null, restaurant1, 89);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateInvalid() throws Exception {
        MenuItem invalid = new MenuItem(MENU_1_ITEM_1_ID, null, null, null, null);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_1_ITEM_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateHtmlUnsafe() throws Exception {
        Restaurant invalid = new Restaurant(MENU_1_RESTAURANT_ID, "dummy", "<script>alert(123)</script>");
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_1_ITEM_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}