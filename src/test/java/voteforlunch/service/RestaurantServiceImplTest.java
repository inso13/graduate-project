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
import voteforlunch.RestaurantTestData;
import voteforlunch.model.Restaurant;
import voteforlunch.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;

import static voteforlunch.RestaurantTestData.*;
import static voteforlunch.UserTestData.*;

import static org.junit.Assert.*;

/**
 * Created by win-7.1 on 10.01.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceImplTest {

    @Autowired
    private RestaurantService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(REST1_ID, ADMIN_ID);
        RestaurantTestData.MATCHER.assertEquals(restaurant, REST1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(REST1_ID, ADMIN_ID);
      RestaurantTestData.MATCHER.assertCollectionEquals(Collections.singletonList(REST2), service.getAll());
    }

    @Test
    public void getAll() throws Exception {
        RestaurantTestData.MATCHER.assertCollectionEquals(RESTAURANTS, service.getAll());
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = getUpdated();
        service.update(updated, ADMIN_ID);
        RestaurantTestData.MATCHER.assertEquals(updated, service.get(REST1_ID, USER_ID));
    }

    @Test
    public void save() throws Exception {
        Restaurant created = getCreated();
        service.save(created, ADMIN_ID);
        RestaurantTestData.MATCHER.assertCollectionEquals(Arrays.asList(created, REST1, REST2), service.getAll());
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(1, ADMIN_ID);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.update(REST1, USER_ID);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(REST1_ID, USER_ID);
    }

}