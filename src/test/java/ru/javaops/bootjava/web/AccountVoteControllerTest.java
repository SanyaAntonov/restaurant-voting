package ru.javaops.bootjava.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountVoteControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getVoteFromHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account/history/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account/history"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllRestaurants() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account/vote/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getRestaurantDishes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account/vote/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    // Works only if your LocalDateTime before 11 a.m.!
    /*@Test
    @WithUserDetails(value = USER_MAIL)
    void create() throws Exception {
        Vote newVote = VoteTestUtil.getNew();
        perform(MockMvcRequestBuilders.post("/api/account/vote/" + MAC_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newVote)))
                .andExpect(status().isOk());
    }*/
}
