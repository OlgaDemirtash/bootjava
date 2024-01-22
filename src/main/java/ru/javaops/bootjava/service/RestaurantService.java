package ru.javaops.bootjava.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.javaops.bootjava.validation.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAllByOrderByName();
    }

    public List<Restaurant> getAllWithMenuItems() {
        return restaurantRepository.findAllRestaurantsWithMenuItemByOrderByName();
    }

    public List<Restaurant> getAllWithMenuItemsByDate(LocalDate issued) {
        return restaurantRepository.findAllWithMenuItemsByMenuIssued(issued);
    }

    @Transactional
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.id());
    }

    @Transactional
    public Restaurant create(Restaurant r) {
        Assert.notNull(r, "restaurant must not be null");
        return restaurantRepository.save(r);
    }
}