package voteforlunch.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;
import voteforlunch.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant, int userId) {


        if (restaurant.isNew()) {
            em.persist(restaurant);

        } else {
            if (get(restaurant.getId(), userId) == null) return null;
        }
        em.merge(restaurant);
        return restaurant;
    }

    @Override
    @Transactional
    public boolean delete(int id) {

        return em.createNamedQuery(Restaurant.DELETE).setParameter("id", id).executeUpdate() != 0;

    }

    @Override
    public Restaurant get(int id, int userId) {
        List<Restaurant> restaurants = em.createNamedQuery(Restaurant.GET, Restaurant.class).setParameter("id", id).getResultList();
        if (restaurants.size() == 0) return null;
        else return DataAccessUtils.singleResult(restaurants);
    }

    @Override
    public List<Restaurant> getAll() {

        return em.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class).getResultList();
    }

    @Override
    public Collection<Dish> getAllDishes(int restId) {
        return em.createNamedQuery(Dish.ALL_SORTED, Dish.class).setParameter("restId", restId).getResultList();
    }
}