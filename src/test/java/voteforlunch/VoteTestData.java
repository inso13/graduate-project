package voteforlunch;

import voteforlunch.matcher.ModelMatcher;
import voteforlunch.model.Dish;
import voteforlunch.model.Vote;

import java.time.LocalDate;

import static voteforlunch.RestaurantTestData.REST1;
import static voteforlunch.UserTestData.USER;
import static voteforlunch.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class VoteTestData {

    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class);

    public static final int VOTE1_ID = START_SEQ + 12;


    public static final Vote VOTE1 = new Vote(VOTE1_ID, LocalDate.of(2017, 2, 1), REST1, USER);
}
