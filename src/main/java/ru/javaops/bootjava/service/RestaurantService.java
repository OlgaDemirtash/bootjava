package ru.javaops.bootjava.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.validation.ValidationUtil;

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

    public List<Restaurant> getAllWithMenu() {
        return restaurantRepository.findAllRestaurantsWithMenuByOrderByName();
    }

    public List<Restaurant> getAllWithVotes() {
        return restaurantRepository.findAllRestaurantsWithVoteByOrderByName();
    }

    public List<Restaurant> getAllWithMenuByDate(LocalDate menuDate) {
        return restaurantRepository.findAllRestaurantsWithMenuByMenu_RegisteredOrderByName(menuDate);
    }

    @Transactional
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.id());
    }

    @Transactional
    public Restaurant create(Restaurant r) {
        Assert.notNull(r, "restaurant must not be null");
        ValidationUtil.checkNew(r);
        return restaurantRepository.save(r);
    }
}