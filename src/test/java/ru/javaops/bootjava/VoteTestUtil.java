package ru.javaops.bootjava;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.web.servlet.MvcResult;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaops.bootjava.RestaurantTestUtil.burgerKing;
import static ru.javaops.bootjava.RestaurantTestUtil.macDonalds;
import static ru.javaops.bootjava.UserTestUtil.user;

public class VoteTestUtil {
    public static final int FIRST_VOTE_ID = 1;
    public static final int SECOND_VOTE_ID = 2;
    public static final Vote firstVote = new Vote(LocalDate.of(2021, 4, 14), user, macDonalds);
    public static final Vote secondVote = new Vote(LocalDate.of(2021, 4, 15), user, burgerKing);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now(), user, macDonalds);
    }

    public static void assertEquals(Vote actual, Vote expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("date").isEqualTo(expected);
    }

    // No id in HATEOAS answer
    public static void assertNoIdEquals(Vote actual, Vote expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id", "date", "password").isEqualTo(expected);
    }

    public static Vote asVote(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, Vote.class);
    }
}
