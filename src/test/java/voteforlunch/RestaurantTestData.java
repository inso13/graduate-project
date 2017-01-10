package voteforlunch;

import voteforlunch.matcher.ModelMatcher;
import voteforlunch.model.Restaurant;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static voteforlunch.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class RestaurantTestData {

    public static final ModelMatcher<Restaurant> MATCHER = new ModelMatcher<>();

    public static final int REST1_ID = START_SEQ + 2;
    public static final int REST2_ID = START_SEQ + 3;

    public static final Restaurant REST1 = new Restaurant(REST1_ID, "MCdonalds");
    public static final Restaurant REST2 = new Restaurant(REST2_ID, "Burger King");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(REST1, REST2);

    public static Restaurant getCreated() {
        return new Restaurant (null, "Созданный ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(REST1_ID,  "Обновленный MCdonalds");
    }
}
