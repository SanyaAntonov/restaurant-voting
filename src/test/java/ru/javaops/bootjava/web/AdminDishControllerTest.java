package ru.javaops.bootjava.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.DishTestUtil;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.repository.DishRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.bootjava.DishTestUtil.*;
import static ru.javaops.bootjava.UserTestUtil.ADMIN_MAIL;
import static ru.javaops.bootjava.util.JsonUtil.writeValue;

public class AdminDishControllerTest extends AbstractControllerTest {
    static final String URL = "/api/v1/restaurant/1/dish/";

    @Autowired
    private DishRepository repository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + BIG_MAC_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonMatcher(bigMac, DishTestUtil::assertNoIdEquals));
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
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + BIG_MAC_ID))
                .andExpect(status().isOk());
        Assertions.assertFalse(repository.findById(BIG_MAC_ID).isPresent());
        Assertions.assertTrue(repository.findById(BIG_TASTY_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Dish newDish = DishTestUtil.getNew();
        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newDish)))
                .andExpect(status().isCreated())
                .andExpect(jsonMatcher(newDish, DishTestUtil::assertNoIdEquals));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Dish updated = DishTestUtil.getUpdated();
        perform(MockMvcRequestBuilders.put(URL + BIG_MAC_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andExpect(status().isOk());
        DishTestUtil.assertEquals(updated, repository.findById(BIG_MAC_ID).orElseThrow());
    }
}
