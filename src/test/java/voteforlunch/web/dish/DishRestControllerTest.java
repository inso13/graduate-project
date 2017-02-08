package voteforlunch.web.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Dish;
import voteforlunch.service.DishService;
import voteforlunch.service.RestaurantService;
import voteforlunch.web.AbstractControllerTest;
import voteforlunch.web.Restaurant.RestaurantRestController;
import voteforlunch.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static voteforlunch.DishTestData.*;
import static voteforlunch.RestaurantTestData.REST1;
import static voteforlunch.RestaurantTestData.REST1_ID;
import static voteforlunch.RestaurantTestData.REST2;
import static voteforlunch.UserTestData.ADMIN_ID;
import static voteforlunch.web.dish.DishRestController.REST_URL;

/**
 * Created by Inso on 08.02.2017.
 */
public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishRestController.REST_URL + '/';
    @Autowired
    private DishService service;
    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + "get/"+DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(DISH1));
    }

    @Test
    public void testDelete() throws Exception {
        AuthorizedUser.setId(100001);
        mockMvc.perform(delete(REST_URL + DISH1_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(DISH2, DISH3, DISH4), service.getAllDishes(REST1_ID));
    }

    @Test
    public void getAllDishes() throws Exception {
        mockMvc.perform(get(REST_URL+REST1_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISHES));
    }

    @Test
    public void create() throws Exception {
        AuthorizedUser.setId(100001);
        Dish created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL+REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Dish returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH1, DISH2, DISH3, DISH4, created), service.getAllDishes(REST1_ID));
    }

    @Test
    public void update() throws Exception {
        AuthorizedUser.setId(100001);
        Dish updated = getUpdated();
        mockMvc.perform(put(REST_URL + REST1_ID+"/"+DISH1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(DISH1_ID, ADMIN_ID));
    }

}