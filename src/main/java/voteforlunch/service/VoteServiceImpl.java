package voteforlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import voteforlunch.model.Dish;
import voteforlunch.model.Vote;
import voteforlunch.repository.VoteRepository;
import voteforlunch.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.Collection;

import static voteforlunch.util.ValidationUtil.checkNotFound;
import static voteforlunch.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Котик on 12.01.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote get(int id, int userId) throws NotFoundException {
        return repository.get(userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(userId), id);
    }

    @Override
    public Collection<Vote> getAllVotes(int restId) {
        return repository.getAllVotes(restId);
}

    @Override
    public Vote update(Vote vote, int userId, int restId) throws NotFoundException {
        Assert.notNull(vote, "Vote must not be null");
        LocalTime time = LocalTime.now();
        if (time.isAfter(LocalTime.of(11, 0))) return null;
        return checkNotFoundWithId(repository.save(vote, userId, restId), vote.getId());
    }

    @Override
    public Vote save(Vote vote, int userId, int restId) {
        Assert.notNull(vote, "Dish must not be null");
        return checkNotFound(repository.save(vote, userId, restId), "Dish must not be null, or not enough rights");
    }
}
