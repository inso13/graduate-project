package voteforlunch.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Dish;
import voteforlunch.model.Vote;
import voteforlunch.service.DishService;
import voteforlunch.service.VoteService;

import java.util.ArrayList;
import java.util.List;

import static voteforlunch.util.ValidationUtil.checkIdConsistent;
import static voteforlunch.util.ValidationUtil.checkNew;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class VoteRestController {
    private static final Logger LOG = LoggerFactory.getLogger(VoteRestController.class);

    @Autowired
    private VoteService service;

    public Vote get() {
        int userId = AuthorizedUser.id();
        LOG.info("get Vote {} for User {}", userId);
        return service.get(userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("delete Vote {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<Vote> getAllVotes(int restId)
    {
        List<Vote> votes = new ArrayList<>();
        votes.addAll(service.getAllVotes(restId));
        return votes;
    }

    public Vote create(Vote vote, int restId) {
        checkNew(vote);
        int userId = AuthorizedUser.id();
        LOG.info("create Vote {} for User {}", vote, userId);
        return service.save(vote, userId, restId);
    }

    public Vote update(Vote vote, int id, int restId) {
        checkIdConsistent(vote, id);
        int userId = AuthorizedUser.id();
        LOG.info("update Vote {} for User {}", vote, userId);
        return service.update(vote, userId, restId);
    }

}
