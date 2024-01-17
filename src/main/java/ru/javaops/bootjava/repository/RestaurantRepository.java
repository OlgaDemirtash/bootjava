package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    List<Restaurant> findAllByOrderByName();

    @EntityGraph(value = "graph.RestaurantWithMenus", type = EntityGraph.EntityGraphType.LOAD)
    List<Restaurant> findAllRestaurantsWithMenuByOrderByName();

    @EntityGraph(value = "graph.RestaurantWithVotes", type = EntityGraph.EntityGraphType.LOAD)
    List<Restaurant> findAllRestaurantsWithVoteByOrderByName();

    @EntityGraph(value = "graph.RestaurantWithMenus", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menus m WHERE m.registered = :registered ORDER BY r.name")
    List<Restaurant> findAllRestaurantsWithMenuByMenu_RegisteredOrderByName(@Param("registered") LocalDate registered);
}