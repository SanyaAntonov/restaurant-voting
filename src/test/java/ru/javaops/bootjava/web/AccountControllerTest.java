package ru.javaops.bootjava.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.model.Role;
import ru.javaops.bootjava.model.User;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account"))
                .andExpect(status().isOk());
    }

    @Test
    void getUnAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/account"))
                .andExpect(status().isOk());
    }

    @Test
    void register() throws Exception {
        User user = new User();
        user.setEmail("new@gmail.com");
        user.setFirstName("New_First");
        user.setLastName("New_Last");
        user.setPassword("newpass");
        user.setRoles(List.of(Role.USER));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/account/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(user)))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        User user = new User();
        user.setId(USER_ID);
        user.setEmail("user_update@gmail.com");
        user.setFirstName("User_First_Update");
        user.setLastName("User_Last_Update");
        user.setPassword("password_update");
        user.setRoles(List.of(Role.USER));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(user)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}