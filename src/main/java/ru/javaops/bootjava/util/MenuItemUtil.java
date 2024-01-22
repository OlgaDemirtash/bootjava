package ru.javaops.bootjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.bootjava.model.MenuItem;
import ru.javaops.bootjava.to.MenuItemTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MenuItemUtil {
    public static MenuItemTo createTo(MenuItem menuItem) {
        return new MenuItemTo(menuItem.getId(), menuItem.getName(), menuItem.getIssued(), menuItem.getRestaurant().getId(), menuItem.getPrice());
    }

    public static List<MenuItemTo> getTos(Collection<MenuItem> menuItems) {
        return menuItems.stream()
                .map(MenuItemUtil::createTo)
                .collect(Collectors.toList());
    }
}