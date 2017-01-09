package voteforlunch.repository;

import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface DishRepository {
    // null if updated Restaurant do not belong to userId
    Dish save(Dish dish, int userId);

    // false if Restaurant do not belong to userId
    boolean delete(int id);

    // null if Restaurant do not belong to userId
    Dish get(int id, int userId);

      Collection<Dish> getAllDishes(int restId);


}
