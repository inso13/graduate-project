package voteforlunch.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Dish;
import voteforlunch.service.DishService;

import java.util.ArrayList;
import java.util.List;

import static voteforlunch.util.ValidationUtil.checkIdConsistent;
import static voteforlunch.util.ValidationUtil.checkNew;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public abstract class DishAbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(DishAbstractController.class);

    @Autowired
    private DishService service;

    public Dish get(int id) {
        int userId = AuthorizedUser.getId();
        LOG.info("get Restaurant {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.getId();
        LOG.info("delete Restaurant {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<Dish> getAllDishes(int restId)
    {
        List<Dish> dishes = new ArrayList<>();
        dishes.addAll(service.getAllDishes(restId));
        return dishes;
    }

    public Dish create(Dish dish, int restId) {
        checkNew(dish);
        int userId = AuthorizedUser.getId();
        LOG.info("create {} for User {}", dish, userId);
        return service.save(dish, userId, restId);
    }

    public void update(Dish dish, int id,  int restId) {
        checkIdConsistent(dish, id);
        int userId = AuthorizedUser.getId();
        LOG.info("update {} for User {}", dish, userId);
        service.update(dish, userId, restId);
    }

}
