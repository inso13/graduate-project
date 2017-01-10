package voteforlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;
import voteforlunch.repository.DishRepository;
import voteforlunch.repository.RestaurantRepository;

import java.util.Collection;

import static voteforlunch.util.ValidationUtil.checkNotFoundWithId;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository repository;

    @Override
    public Dish get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }


    @Override
    public Dish update(Dish dish, int userId) {
        Assert.notNull(dish, "Dish must not be null");
        return checkNotFoundWithId(repository.save(dish, userId), dish.getId());
    }

    @Override
    public Dish save(Dish dish, int userId) {
        Assert.notNull(dish, "Dish must not be null");
        return repository.save(dish, userId);
    }

    @Override
    public Collection<Dish> getAllDishes(int restId) {
        return repository.getAllDishes(restId);
    }
}
