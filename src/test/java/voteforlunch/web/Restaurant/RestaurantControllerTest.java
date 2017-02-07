package voteforlunch.web.Restaurant;

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
import static voteforlunch.RestaurantTestData.REST1;
import static voteforlunch.RestaurantTestData.REST1_ID;

/**
 * Created by win-7.1 on 07.02.2017.
 */
public class RestaurantControllerTest extends AbstractControllerTest{

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("rest_select"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/rest_select.jsp"))
                .andExpect(model().attribute("restaurants", hasSize(2)))
                .andExpect(model().attribute("restaurants", hasItem(
                        allOf(
                                hasProperty("id", is(REST1_ID)),
                                hasProperty("name", is(REST1.getName()))
                        )
                )));
    }

}