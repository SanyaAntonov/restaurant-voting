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

class UserControllerTest extends AbstractControllerTest {
    private final String URL = "/api/users/";

//    @Test
//    @WithUserDetails(value = ADMIN_MAIL)
//    void get() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(URL + USER_ID))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @WithUserDetails(value = ADMIN_MAIL)
//    void getAll() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(URL))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithUserDetails(value = ADMIN_MAIL)
//    void getByEmail() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(URL + "search/by-email?email=" + ADMIN_MAIL))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithUserDetails(value = ADMIN_MAIL)
//    void delete() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete(URL + USER_ID))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithUserDetails(value = ADMIN_MAIL)
//    void create() throws Exception {
//        User user = new User();
//        user.setEmail("new@gmail.com");
//        user.setFirstName("New_First");
//        user.setLastName("New_Last");
//        user.setPassword("newpass");
//        user.setRoles(List.of(Role.USER));
//
//        mockMvc.perform(MockMvcRequestBuilders.post(URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(user)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithUserDetails(value = ADMIN_MAIL)
//    void update() throws Exception {
//        User user = new User();
//        user.setEmail("new@gmail.com");
//        user.setFirstName("New_First");
//        user.setLastName("New_Last");
//        user.setPassword("newpass");
//        user.setRoles(List.of(Role.USER));
//
//        mockMvc.perform(MockMvcRequestBuilders.put(URL + USER_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(user)))
//                .andExpect(status().isOk());
//    }
}