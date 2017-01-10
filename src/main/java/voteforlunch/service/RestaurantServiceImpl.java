package voteforlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;
import voteforlunch.model.User;
import voteforlunch.repository.RestaurantRepository;

import java.time.LocalDateTime;
import java.util.Collection;

import static voteforlunch.util.ValidationUtil.checkNotFoundWithId;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }


    @Override
    public Collection<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant update(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        return checkNotFoundWithId(repository.save(restaurant, userId), restaurant.getId());
    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        return repository.save(restaurant, userId);
    }

    @Override
    public Collection<Dish> getAllDishes(int restId) {
        return repository.getAllDishes(restId);
    }
}
