package voteforlunch;

import voteforlunch.matcher.ModelMatcher;
import voteforlunch.model.Dish;
import voteforlunch.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static voteforlunch.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class DishTestData {

    public static final ModelMatcher<Dish> MATCHER = ModelMatcher.of(Dish.class);

    public static final int DISH1_ID = START_SEQ + 4;


    public static final Dish DISH1 = new Dish(DISH1_ID, "Биг мак", 130);
    public static final Dish DISH2 = new Dish(DISH1_ID+1, "Биг тейсти", 220);
    public static final Dish DISH3 = new Dish(DISH1_ID+2, "Картошка", 80);
    public static final Dish DISH4 = new Dish(DISH1_ID+3, "Кока кола", 65);
    public static final Dish DISH5 = new Dish(DISH1_ID+4, "Биг кинг", 120);
    public static final Dish DISH6 = new Dish(DISH1_ID+5, "Воппер", 140);
    public static final Dish DISH7 = new Dish(DISH1_ID+6, "Пепси кола", 80);
    public static final Dish DISH8 = new Dish(DISH1_ID+7, "Пирожок", 70);

    public static Dish getCreated() {
        return new Dish ("Новая еда", 100500);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID,  "Обновленный Биг-мак", 135);
    }
}
