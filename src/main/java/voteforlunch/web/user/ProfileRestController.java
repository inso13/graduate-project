package voteforlunch.web.user;

import org.springframework.stereotype.Controller;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.User;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(AuthorizedUser.getId());
    }

    public void delete() {
        super.delete(AuthorizedUser.getId());
    }

    public void update(User user) {
        super.update(user, AuthorizedUser.getId());
    }
}