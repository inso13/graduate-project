package voteforlunch.web.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;
import voteforlunch.model.User;
import voteforlunch.web.user.UserController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import static voteforlunch.web.Restaurant.RestaurantRestController.REST_URL;

/**
 * Created by win-7.1 on 25.01.2017.
 */
@RestController
@RequestMapping(value = REST_URL)
public class RestaurantRestController extends RestaurantAbstractController{
    static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    public Restaurant create(Restaurant meal) {
        return super.create(meal);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = super.create(restaurant);

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(uriOfNewResource);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        super.update(restaurant, id);
    }
}
