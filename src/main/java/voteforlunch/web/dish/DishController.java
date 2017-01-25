package voteforlunch.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Dish;
import voteforlunch.web.vote.VoteController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by win-7.1 on 25.01.2017.
 */
@Controller
@RequestMapping(value = "/dishes")
public class DishController extends DishAbstractController {

    @Autowired
    VoteController voteController;
    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restId"));
        return Integer.valueOf(paramId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getAll(Model model, HttpServletRequest request) {
        model.addAttribute("dishes", super.getAllDishes(getId(request)));
        model.addAttribute("restId",getId(request));
        request.setAttribute("votes", voteController.getAllVotes(getId(request)).size());
        if (AuthorizedUser.isIsAdmin()) return "dishes_edit_vote";
        else return "dishes_vote";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String get(HttpServletRequest request) {
        int restId = Integer.parseInt(request.getParameter("restId"));
        return "redirect:/dishes/get?restId="+restId;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {
        final Dish dish = new Dish("New Dish", 100);
        model.addAttribute("dish", dish);
        model.addAttribute("restId", getId(request));
        return "dish_edit_menu";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, HttpServletRequest request) {
        final Dish dish = get(Integer.parseInt(request.getParameter("id")));
        model.addAttribute("dish", dish);
        model.addAttribute("restId", getId(request));
        return "dish_edit_menu";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int restId = getId(request);
        super.delete(id);
        return "redirect:/dishes/get?restId="+restId;}

    @RequestMapping(method = RequestMethod.POST)
    public String add (HttpServletRequest request) {
        final Dish dish = new Dish(
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("price")));
        if (request.getParameter("id").isEmpty()) {
            super.create(dish, getId(request));
        } else {
            super.update(dish, (Integer.parseInt(request.getParameter("id"))), getId(request));
        }
        int restId = getId(request);
        return "redirect:/dishes/get?restId="+restId;}

}
