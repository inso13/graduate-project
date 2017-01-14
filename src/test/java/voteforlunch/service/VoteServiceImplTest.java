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
import static org.junit.Assert.*;
import static voteforlunch.UserTestData.USER_ID;
import static voteforlunch.VoteTestData.MATCHER;
import static voteforlunch.VoteTestData.VOTE1;
import static voteforlunch.VoteTestData.VOTE1_ID;

/**
 * Created by Inso on 14.01.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceImplTest {

    @Autowired
    private VoteService service;

    @Test
    public void get() throws Exception {
        Vote vote = service.get(USER_ID);
        MATCHER.assertEquals(vote, VOTE1);
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void getAllVotes() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

}