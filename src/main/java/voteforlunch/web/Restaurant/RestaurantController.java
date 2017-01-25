package voteforlunch.web.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Restaurant;
import voteforlunch.model.User;
import voteforlunch.web.user.UserController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by win-7.1 on 25.01.2017.
 */
@Controller
@RequestMapping(value = "/restaurants")
public class RestaurantController extends RestaurantAbstractController{
    @Override
    public Restaurant get(int id) {
        return super.get(id);
    }

    @Autowired
    UserController userController;

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("restaurants", super.getAll());
        if (AuthorizedUser.isIsAdmin()) return "rest_edit_select";
        else return "rest_select";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        final Restaurant restaurant = new Restaurant("New Restaurant");
        model.addAttribute("restaurant", restaurant);
        return "restaurant_edit_menu";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, HttpServletRequest request) {
        final Restaurant restaurant = get(getId(request));
        model.addAttribute("restaurant", restaurant);
        return "restaurant_edit_menu";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        int id = getId(request);
        super.delete(id);
        return "redirect:/restaurants";}

    @RequestMapping(method = RequestMethod.POST)
    public String add (HttpServletRequest request) {
        final Restaurant restaurant = new Restaurant(
                request.getParameter("name"));
        if (request.getParameter("id").isEmpty()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, getId(request));
        }
        return "redirect:restaurants";}

    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public String vote(Model model, HttpServletRequest request) {
    {
        Restaurant restaurant = get(Integer.parseInt(request.getParameter("restId")));
        model.addAttribute("restaurant", restaurant);
        User user = userController.get(AuthorizedUser.getId());
        model.addAttribute("user", user);
        return "vote";
    }

}}
