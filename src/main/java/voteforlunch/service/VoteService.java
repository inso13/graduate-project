package voteforlunch.service;

import voteforlunch.model.Dish;
import voteforlunch.model.Vote;
import voteforlunch.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface VoteService {
    Vote get(int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<Vote> getAllVotes(int restId);

    Vote update(Vote vote, int userId, int restId) throws NotFoundException;

    Vote save(Vote vote, int userId, int restId);
}
