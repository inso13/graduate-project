package voteforlunch.repository;

import voteforlunch.model.Dish;
import voteforlunch.model.Vote;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface VoteRepository {
    // null if updated Restaurant do not belong to userId
    Vote save(Vote vote, int userId, int restId);

    // false if Restaurant do not belong to userId
    boolean delete(int userId);

    // null if Restaurant do not belong to userId
    Vote get(int userId);

    Collection<Vote> getAllVotes(int restId);


}
