package voteforlunch.service;

import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;
import voteforlunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface RestaurantService {
    Restaurant get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<Restaurant> getAll();
    Collection<Dish> getAllDishes(int restId);

    Restaurant update(Restaurant restaurant, int userId) throws NotFoundException;

    Restaurant save(Restaurant restaurant, int userId);
}
