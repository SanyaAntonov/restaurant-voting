package ru.javaops.bootjava;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestUtil {
    public static final int MAC_ID = 1;
    public static final int KING_ID = 2;
    public static final String MAC_DONALDS = "MacDonalds";
    public static final String BURGER_KING = "BurgerKing";
    public static final Restaurant macDonalds = new Restaurant(MAC_ID, MAC_DONALDS, "Pushkin str");
    public static final Restaurant burgerKing = new Restaurant(KING_ID, BURGER_KING, "Lomonosov str");

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant", "New Address");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(MAC_ID, "Updated", "Updated");
    }

    public static void assertEquals(Restaurant actual, Restaurant expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    // No id in HATEOAS answer
    public static void assertNoIdEquals(Restaurant actual, Restaurant expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }

    public static Restaurant asRestaurant(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, Restaurant.class);
    }

    public static ResultMatcher jsonMatcher(Restaurant expected, BiConsumer<Restaurant, Restaurant> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asRestaurant(mvcResult), expected);
    }
}
