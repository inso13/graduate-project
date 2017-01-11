package voteforlunch.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import voteforlunch.DishTestData;
import voteforlunch.model.Dish;
import voteforlunch.util.exception.NotFoundException;

import java.util.Arrays;

import static voteforlunch.DishTestData.*;
import static voteforlunch.UserTestData.*;

import static org.junit.Assert.*;

/**
 * Created by Котик on 10.01.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceImplTest {

    @Autowired
    private DishService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void get() throws Exception {
        Dish dish = service.get(DISH1_ID, USER_ID);
        DishTestData.MATCHER.assertEquals(dish, DISH1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(DISH8.getId(), ADMIN_ID);
        DishTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(DISH5,DISH6,DISH7), service.getAllDishes(100003));
    }

    @Test
    public void update() throws Exception {
        Dish updated = getUpdated();
        service.update(updated, ADMIN_ID, 100002);
        DishTestData.MATCHER.assertEquals(updated, service.get(100004, ADMIN_ID));
    }

    @Test
    public void save() throws Exception {
        Dish created = getCreated();
        service.save(created, ADMIN_ID, 100002);
        DishTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(DISH1, DISH2,DISH3,DISH4, created),
                service.getAllDishes(100002)
        );
    }

    @Test
    public void getAllDishes() throws Exception {
        DishTestData.MATCHER.assertCollectionEquals(Arrays.asList(DISH1, DISH2,DISH3,DISH4), service.getAllDishes(100002));
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(1, ADMIN_ID);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.update(DISH3, USER_ID, 100002);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(DISH3.getId(), USER_ID);
    }

    @Test
    public void saveByWrongUser() throws Exception {
        thrown.expect(NotFoundException.class);
        Dish created = getCreated();
        service.save(created, USER_ID, 100003);
    }
}