package ru.javaops.bootjava.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.model.Dish;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminDishControllerTest extends AbstractControllerTest {
    private final String URL = "/api/v1/restaurant/1/dish/";

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void crud() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL + BIG_MAC_ID))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + BIG_MAC_ID))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Dish dish = new Dish(null, "New Dish", 999, macDonalds);
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dish)))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Dish dish = new Dish(BIG_MAC_ID, "Updated", 999, macDonalds);
        mockMvc.perform(MockMvcRequestBuilders.put(URL + BIG_MAC_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dish)))
                .andExpect(status().isOk());
    }
}
