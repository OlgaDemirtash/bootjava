package ru.javaops.bootjava.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Menu;

import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {

    List<Menu> findAllByMenuIdRegisteredOrderByMenuIdRestaurantId(Date registered);

    List<Menu> findAllByMenuIdRegisteredAndMenuIdRestaurantId(Date registered, int restaurant_id);

    List<Menu> findAllByMenuIdRestaurantId(int restaurant_id);
}