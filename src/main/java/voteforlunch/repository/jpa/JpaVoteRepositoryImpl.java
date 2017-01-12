package voteforlunch.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import voteforlunch.model.*;
import voteforlunch.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Inso on 11.01.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaVoteRepositoryImpl implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int restId) {

        Restaurant restRef = em.getReference(Restaurant.class, restId);
        vote.setRestaurant(restRef);
        User userRef = em.getReference(User.class, restId);
        vote.setUser(userRef);
        if (vote.isNew() && (get(userId)==null)) {
            em.persist(vote);

        } else {
            if (get(userId) == null) return null;
        }
        em.merge(vote);
        return vote;
    }

    @Override
    public boolean delete(int userId) {
        return checkForAdmin(userId) && em.createNamedQuery(Vote.DELETE).setParameter("userId", userId).executeUpdate() != 0;
    }

    @Override
    public Vote get(int userId) {
        List<Vote> votes = em.createNamedQuery(Vote.GET, Vote.class).setParameter("userId", userId).getResultList();
        if (votes.size() == 0) return null;
        else return DataAccessUtils.singleResult(votes);
    }

    @Override
    public Collection<Vote> getAllVotes(int restId) {
        LocalDate dateTime = LocalDate.now();
        return em.createNamedQuery(Vote.ALL_SORTED, Vote.class).setParameter("restId", restId).setParameter("dateTime", dateTime).getResultList();
    }

    private boolean checkForAdmin(int userId)
    {
        User user = em.createNamedQuery(User.GET, User.class).setParameter("id", userId).getSingleResult();
        Set<Role> roleSet = user.getRoles();
        return roleSet.contains(Role.ROLE_ADMIN);
    }
}
