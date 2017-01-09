package voteforlunch.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;
import voteforlunch.repository.DishRepository;
import voteforlunch.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish, int userId) {


        if (dish.isNew()) {
            em.persist(dish);

        } else {
            if (get(dish.getId(), userId) == null) return null;
        }
        em.merge(dish);
        return dish;
    }

    @Override
    @Transactional
    public boolean delete(int id) {

        return em.createNamedQuery(Dish.DELETE).setParameter("id", id).executeUpdate() != 0;

    }

    @Override
    public Dish get(int id, int userId) {
        List<Dish> dishes = em.createNamedQuery(Dish.GET, Dish.class).setParameter("id", id).getResultList();
        if (dishes.size() == 0) return null;
        else return DataAccessUtils.singleResult(dishes);
    }


    @Override
    public Collection<Dish> getAllDishes(int restId) {
        return em.createNamedQuery(Dish.ALL_SORTED, Dish.class).setParameter("restId", restId).getResultList();
    }
}