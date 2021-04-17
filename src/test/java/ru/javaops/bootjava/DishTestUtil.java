package ru.javaops.bootjava;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaops.bootjava.RestaurantTestUtil.macDonalds;

public class DishTestUtil {
    public static final int BIG_MAC_ID = 2;
    public static final int BIG_TASTY_ID = 4;
    public static final String BIG_MAC = "BigMac";
    public static final String BIG_TASTY = "BigTasty";
    public static final Dish bigMac = new Dish(BIG_MAC_ID, BIG_MAC, 130, macDonalds);
    public static final Dish bigTasty = new Dish(BIG_TASTY_ID, BIG_TASTY, 230, macDonalds);

    public static Dish getNew() {
        return new Dish(null, "New Dish", 999, macDonalds);
    }

    public static Dish getUpdated() {
        return new Dish(BIG_MAC_ID, "Updated", 999, macDonalds);
    }

    public static void assertEquals(Dish actual, Dish expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    // No id in HATEOAS answer
    public static void assertNoIdEquals(Dish actual, Dish expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }

    public static Dish asDish(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, Dish.class);
    }

    public static ResultMatcher jsonMatcher(Dish expected, BiConsumer<Dish, Dish> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asDish(mvcResult), expected);
    }
}
