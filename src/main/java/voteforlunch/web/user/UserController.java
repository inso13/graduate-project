package voteforlunch.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Role;
import voteforlunch.model.User;
import voteforlunch.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by win-7.1 on 25.01.2017.
 */
@Controller
public class UserController extends AbstractUserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        User currentUser = service.get(AuthorizedUser.getId());
        Set<Role> roleSet = currentUser.getRoles();
        if (roleSet.contains(Role.ROLE_ADMIN)) {
           AuthorizedUser.setIsAdmin(true);}
        else AuthorizedUser.setIsAdmin(false);
        return "redirect:restaurants";
    }
}
