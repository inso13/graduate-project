package voteforlunch.web.dish;

import org.junit.Ignore;
import org.junit.Test;
import voteforlunch.web.AbstractControllerTest;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static voteforlunch.DishTestData.DISH1;
import static voteforlunch.DishTestData.DISH1_ID;
import static voteforlunch.RestaurantTestData.REST1;
import static voteforlunch.RestaurantTestData.REST1_ID;

/**
 * Created by win-7.1 on 07.02.2017.
 */
public class DishControllerTest extends AbstractControllerTest{

    @Test
    @Ignore
    public void getAll() throws Exception {
        mockMvc.perform(get("/dishes/get?restId=100003"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("dishes_vote"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/dishes_vote.jsp"))
                .andExpect(model().attribute("dishes", hasSize(4)))
                .andExpect(model().attribute("dishes", hasItem(
                        allOf(
                                hasProperty("id", is(DISH1_ID)),
                                hasProperty("description", is(DISH1.getDescription()))
                        )
                )));
    }

}