package voteforlunch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import voteforlunch.model.Vote;
import voteforlunch.VoteTestData;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static voteforlunch.RestaurantTestData.REST1_ID;
import static voteforlunch.UserTestData.ADMIN_ID;
import static voteforlunch.UserTestData.USER_ID;
import static voteforlunch.VoteTestData.MATCHER;
import static voteforlunch.VoteTestData.VOTE1;
import static voteforlunch.VoteTestData.VOTE1_ID;

/**
 * Created by Inso on 14.01.2017.
 */

public class VoteServiceImplTest extends AbstractVoteServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void get() throws Exception {
        Vote vote = service.get(USER_ID);
        MATCHER.assertEquals(vote, VOTE1);
    }
}