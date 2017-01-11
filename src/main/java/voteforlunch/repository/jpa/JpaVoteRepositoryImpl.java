package voteforlunch.repository.jpa;

import voteforlunch.model.Dish;
import voteforlunch.model.Vote;
import voteforlunch.repository.VoteRepository;

import java.util.Collection;

/**
 * Created by Inso on 11.01.2017.
 */
public class JpaVoteRepositoryImpl implements VoteRepository {
    @Override
    public Vote save(Vote vote, int userId, int restId) {
        return null;
    }

    @Override
    public Vote delete(int id, int userId) {
        return null;
    }

    @Override
    public Vote get(int id, int userId) {
        return null;
    }

    @Override
    public Collection<Dish> getAllDishes(int restId) {
        return null;
    }
}
