package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    List<Restaurant> findAllByOrderByName();

    @Query("SELECT r, m FROM Restaurant r JOIN Menu m ON r.id = m.restaurant.id ORDER BY r.name DESC, m.registered DESC, m.id DESC")
    List<Restaurant> findAllRestaurantsWithMenu();

    @Query("SELECT r, m FROM Restaurant r JOIN Vote m ON r.id = m.restaurant.id")
    List<Restaurant> findAllRestaurantsWithVote();

    List<Restaurant> findByMenus_RegisteredOrderByName(@Param("registered") LocalDate registered);
}