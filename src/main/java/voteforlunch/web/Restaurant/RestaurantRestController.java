package voteforlunch.web.Restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import voteforlunch.AuthorizedUser;

import voteforlunch.model.Restaurant;
import voteforlunch.util.DateTimeUtil;
import voteforlunch.util.MealsUtil;
import voteforlunch.service.RestaurantService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static voteforlunch.util.ValidationUtil.checkIdConsistent;
import static voteforlunch.util.ValidationUtil.checkNew;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class RestaurantRestController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantRestController.class);

    @Autowired
    private RestaurantService service;

    public Restaurant get(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("get Restaurant {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("delete Restaurant {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<Restaurant> getAll() {
        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.addAll(service.getAll(userId));
        return restaurants;
    }

    public Restaurant create(Restaurant meal) {
        checkNew(meal);
        int userId = AuthorizedUser.id();
        LOG.info("create {} for User {}", meal, userId);
        return service.save(meal, userId);
    }

    public void update(Restaurant restaurant, int id) {
        checkIdConsistent(restaurant, id);
        int userId = AuthorizedUser.id();
        LOG.info("update {} for User {}", restaurant, userId);
        service.update(restaurant, userId);
    }

}
