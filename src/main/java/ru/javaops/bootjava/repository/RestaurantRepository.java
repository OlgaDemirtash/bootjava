package ru.javaops.bootjava.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Cacheable("restaurants")
    List<Restaurant> findAllByOrderByName();

    @EntityGraph(value = "graph.RestaurantWithMenuItems", type = EntityGraph.EntityGraphType.LOAD)
    List<Restaurant> findAllRestaurantsWithMenuItemByOrderByName();

    @EntityGraph(value = "graph.RestaurantWithMenuItems", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menuItems m WHERE m.issued = :issued ORDER BY r.name")
    List<Restaurant> findAllWithMenuItemsByMenuIssued(@Param("issued") LocalDate issued);
}