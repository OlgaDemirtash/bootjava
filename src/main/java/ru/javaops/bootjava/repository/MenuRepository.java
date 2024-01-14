package ru.javaops.bootjava.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {

    List<Menu> findAllByRegisteredOrderByRestaurantId(LocalDate registered);

    List<Menu> findAllByRegisteredAndRestaurantIdOrderById(LocalDate registered, int restaurant_id);

    List<Menu> findAllByRestaurantId(int restaurant_id);

    boolean existsByRestaurantIdAndRegistered(int restaurantId, LocalDate localDate);
}