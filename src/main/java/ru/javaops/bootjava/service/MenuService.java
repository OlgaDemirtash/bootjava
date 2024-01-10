package ru.javaops.bootjava.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Menu;
import ru.javaops.bootjava.repository.MenuRepository;
import ru.javaops.bootjava.repository.UserRepository;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    @Transactional
    public Menu save(Menu menu) {
        //restaurant.setUser(userRepository.getExisted(userId));
        return menuRepository.save(menu);
    }
}