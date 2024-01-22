package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.bootjava.model.MenuItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.javaops.bootjava.validation.ValidationUtil.checkNotFoundWithId;

@Transactional(readOnly = true)
public interface MenuItemRepository extends BaseRepository<MenuItem> {

    List<MenuItem> findAllByIssuedAndRestaurantIdOrderById(LocalDate issued, int restaurant_id);

    List<MenuItem> findAllByRestaurantId(int restaurant_id);

    List<MenuItem> findAllByIssuedGreaterThanEqualAndIssuedLessThanEqual(LocalDate start, LocalDate end);

    boolean existsByRestaurantIdAndIssued(int restaurantId, LocalDate issued);

    @EntityGraph(value = "graph.menuItemWithRestaurant", type = EntityGraph.EntityGraphType.LOAD)
    Optional<MenuItem> findById(int id);

    @Transactional
    default void prepareAndSave(MenuItem menuItem) {
        Assert.notNull(menuItem, "menu position must not be null");
        checkNotFoundWithId(save(menuItem), menuItem.id());
    }
}