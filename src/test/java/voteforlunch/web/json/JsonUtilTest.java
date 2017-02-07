package voteforlunch.web.json;

import org.junit.Test;
import voteforlunch.DishTestData;
import voteforlunch.RestaurantTestData;
import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;


import java.util.List;

/**
 * GKislin
 * 22.07.2015.
 */
public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(DishTestData.DISH1);
        System.out.println(json);
        Dish dish = JsonUtil.readValue(json, Dish.class);
        DishTestData.MATCHER.assertEquals(DishTestData.DISH1, dish);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(RestaurantTestData.RESTAURANTS);
        System.out.println(json);
        List<Restaurant> restaurants = JsonUtil.readValues(json, Restaurant.class);
        RestaurantTestData.MATCHER.assertCollectionEquals(RestaurantTestData.RESTAURANTS, restaurants);
    }
}