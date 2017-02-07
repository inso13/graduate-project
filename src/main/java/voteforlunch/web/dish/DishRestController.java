package voteforlunch.web.dish;

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
import voteforlunch.web.vote.VoteController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import static voteforlunch.web.dish.DishRestController.REST_URL;

/**
 * Created by win-7.1 on 25.01.2017.
 */
@RestController
@RequestMapping(value = REST_URL)
public class DishRestController extends DishAbstractController {
    static final String REST_URL = "/rest/dishes";

    @Override
    @GetMapping(value = "get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllDishes(@PathVariable("id") int restId) {
        return super.getAllDishes(restId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @RequestBody int restId) {
        Dish created = super.create(dish, restId);

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(uriOfNewResource);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    public Dish create(Dish dish, int restId) {
        return super.create(dish, restId);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id, @RequestBody int restId) {
        super.update(dish, id, restId);
    }
}
