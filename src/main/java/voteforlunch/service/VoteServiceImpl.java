package voteforlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import voteforlunch.model.Vote;
import voteforlunch.repository.VoteRepository;
import voteforlunch.util.exception.NotFoundException;

import java.time.LocalDate;
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
    public Vote get(int userId) throws NotFoundException {
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
        LocalDate voteDate = repository.get(userId).getDateTime();

        if (!voteDate.equals(LocalDate.now())) return checkNotFoundWithId(repository.save(vote, userId, restId), vote.getId());
        if (time.isBefore(LocalTime.of(11, 0))) {return checkNotFoundWithId(repository.save(vote, userId, restId), vote.getId());}
        return null;
    }

    @Override
    public Vote save(Vote vote, int userId, int restId) {
        Assert.notNull(vote, "Dish must not be null");
        return checkNotFound(repository.save(vote, userId, restId), "Dish must not be null, or not enough rights");
    }
}
