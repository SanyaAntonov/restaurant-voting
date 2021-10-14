package ru.javaops.bootjava.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Restaurant;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    protected final String USER_MAIL = "user@gmail.com";
    protected final String ADMIN_MAIL = "admin@gmail.com";
    protected final int USER_ID = 1;
    protected final int MAC_ID = 1;
    protected final int BIG_MAC_ID = 2;
    protected final Restaurant macDonalds = new Restaurant(MAC_ID, "MacDonalds", "Pushkin str");
}
