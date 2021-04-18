package ru.javaops.bootjava.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.RestaurantTestUtil;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.bootjava.RestaurantTestUtil.*;
import static ru.javaops.bootjava.UserTestUtil.ADMIN_MAIL;
import static ru.javaops.bootjava.util.JsonUtil.writeValue;

public class AdminRestaurantControllerTest extends AbstractControllerTest {
    static final String URL = "/api/v1/restaurant/";

    @Autowired
    private RestaurantRepository repository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + MAC_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonMatcher(macDonalds, RestaurantTestUtil::assertNoIdEquals));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        // TODO check content yourself
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Restaurant newRest = RestaurantTestUtil.getNew();
        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newRest)))
                .andExpect(status().isCreated())
                .andExpect(jsonMatcher(newRest, RestaurantTestUtil::assertNoIdEquals));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Restaurant updated = RestaurantTestUtil.getUpdated();
        perform(MockMvcRequestBuilders.put(URL + MAC_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andExpect(status().isOk());
        RestaurantTestUtil.assertEquals(updated, repository.findById(MAC_ID).orElseThrow());
    }
}
