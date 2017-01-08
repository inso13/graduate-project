package voteforlunch.repository;

import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface RestaurantRepository {
    // null if updated Restaurant do not belong to userId
    Restaurant save(Restaurant restaurant, int userId);

    // false if Restaurant do not belong to userId
    boolean delete(int id);

    // null if Restaurant do not belong to userId
    Restaurant get(int id, int userId);

    // ORDERED dateTime
    Collection<Restaurant> getAll();

    Collection<Dish> getAllDishes(int restId);


}
