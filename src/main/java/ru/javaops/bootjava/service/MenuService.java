package ru.javaops.bootjava.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.bootjava.model.Menu;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.MenuRepository;
import ru.javaops.bootjava.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.javaops.bootjava.validation.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional
    public Menu save(Menu menu) {
        //restaurant.setUser(userRepository.getExisted(userId));
        return menuRepository.save(menu);
    }

    public List<Menu> getAllByDate(LocalDate registered, int restaurantId) {
        return menuRepository.findAllByRegisteredAndRestaurantIdOrderById(registered, restaurantId);
    }

    @Transactional
    public void update(Menu menu) {
        Assert.notNull(menu, "menu position must not be null");
        checkNotFoundWithId(menuRepository.save(menu), menu.id());
    }
}