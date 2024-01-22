package ru.javaops.bootjava.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.bootjava.error.NotFoundException;
import ru.javaops.bootjava.model.MenuItem;
import ru.javaops.bootjava.repository.MenuItemRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.to.MenuItemTo;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuItemService {

    private final MenuItemRepository repository;

    private final RestaurantRepository restaurantRepository;

    public List<MenuItem> getAllByDate(LocalDate registered, int restaurantId) {
        return repository.findAllByIssuedAndRestaurantIdOrderById(registered, restaurantId);
    }

    public List<MenuItem> getAll(int restaurantId) {
        return repository.findAllByRestaurantId(restaurantId);
    }

    public List<MenuItem> getAllBetween(LocalDate start, LocalDate end) {
        return repository.findAllByIssuedGreaterThanEqualAndIssuedLessThanEqual(start, end);
    }

    @Transactional
    public MenuItem create(MenuItemTo menuItemTo) {
        Assert.notNull(menuItemTo, "menuItemTo must not be null");
        int restaurantId = menuItemTo.getRestaurantId();
        LocalDate issue = menuItemTo.getIssued();
        MenuItem menuItem = new MenuItem(null, menuItemTo.getName(), (issue == null ? LocalDate.now() : issue), restaurantRepository.getReferenceById(restaurantId), menuItemTo.getPrice());
        return repository.save(menuItem);
    }

    public MenuItem getByIdWithRestaurant(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant with id=" + id + " not found"));
    }
}