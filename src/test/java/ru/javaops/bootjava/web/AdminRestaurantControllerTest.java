package ru.javaops.bootjava.web;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.model.Restaurant;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminRestaurantControllerTest extends AbstractControllerTest {
    private final String URL = "/api/v1/restaurant/";

//    @Test
//    @Order(1)
//    @WithUserDetails(value = ADMIN_MAIL)
//    void get() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(URL + MAC_ID))
//                .andExpect(status().isOk());
//    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + MAC_ID))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Restaurant newRest = new Restaurant(null, "New Restaurant", "New Address");
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(newRest)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Restaurant updated = new Restaurant(MAC_ID, "Updated", "Updated");
        mockMvc.perform(MockMvcRequestBuilders.put(URL + MAC_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(updated)))
                .andExpect(status().isOk());
    }
}
