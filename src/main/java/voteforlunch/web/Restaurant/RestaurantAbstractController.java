package voteforlunch.web.Restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import voteforlunch.AuthorizedUser;

import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;
import voteforlunch.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

import static voteforlunch.util.ValidationUtil.checkIdConsistent;
import static voteforlunch.util.ValidationUtil.checkNew;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public abstract class RestaurantAbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantAbstractController.class);

    @Autowired
    private RestaurantService service;

    public Restaurant get(int id) {
        int userId = AuthorizedUser.getId();
        LOG.info("get Restaurant {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.getId();
        LOG.info("delete Restaurant {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<Restaurant> getAll() {
        int userId = AuthorizedUser.getId();
        LOG.info("getAll for User {}", userId);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.addAll(service.getAll());
        return restaurants;
    }

    public List<Dish> getAllDishes(int restId)
    {
        List<Dish> dishes = new ArrayList<>();
        dishes.addAll(service.getAllDishes(restId));
        return dishes;
    }

    public Restaurant create(Restaurant meal) {
        checkNew(meal);
        int userId = AuthorizedUser.getId();
        LOG.info("create {} for User {}", meal, userId);
        return service.save(meal, userId);
    }

    public void update(Restaurant restaurant, int id) {
        checkIdConsistent(restaurant, id);
        int userId = AuthorizedUser.getId();
        LOG.info("update {} for User {}", restaurant, userId);
        service.update(restaurant, userId);
    }

}
