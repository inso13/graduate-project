package voteforlunch.web.user;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.User;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile/users";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.getId());
    }

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        super.update(user, AuthorizedUser.getId());
    }
}