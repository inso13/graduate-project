package voteforlunch.service;

import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;
import voteforlunch.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface DishService {
    Dish get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<Dish> getAllDishes(int restId);

    Dish update(Dish dish, int userId) throws NotFoundException;

    Dish save(Dish dish, int userId);
}
