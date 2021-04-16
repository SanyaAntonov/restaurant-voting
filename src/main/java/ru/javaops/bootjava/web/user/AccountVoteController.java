package ru.javaops.bootjava.web.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.javaops.bootjava.AuthUser;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
@Slf4j
@Tag(name = "Vote Controller")
public class AccountVoteController {
    private final VoteRepository voteRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;


    @GetMapping("/vote")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.getAll()
                .orElseThrow(() -> new NotFoundException("Restaurants not found"));

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/vote/{restId}")
    public ResponseEntity<List<Dish>> getAllRestaurantDishes(@PathVariable("restId") int id) {
        List<Dish> dishes = dishRepository.getAllByRestaurant(id)
                .orElseThrow(() -> new NotFoundException("Dishes not found"));

        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Vote>> getVotingHistory(@AuthenticationPrincipal AuthUser authUser) {
        List<Vote> votingHistory = voteRepository.getVotingHistory(authUser.id())
                .orElseThrow(() -> new NotFoundException("voting history not found"));
        return new ResponseEntity<>(votingHistory, HttpStatus.OK);
    }

    @GetMapping("/history/{voteId}")
    public ResponseEntity<Vote> getVote(@AuthenticationPrincipal AuthUser authUser,
                                        @PathVariable("voteId") int id) {
        Vote voteById = voteRepository.getVoteById(authUser.id(), id)
                .orElseThrow(() -> new NotFoundException("Vote not found"));

        return new ResponseEntity<>(voteById, HttpStatus.OK);
    }

    @PostMapping("/vote/{restId}")
    public ResponseEntity<Vote> createOrUpdateVote(@AuthenticationPrincipal AuthUser authUser,
                                           @PathVariable("restId") int id) {
        boolean mutable = LocalTime.now().isBefore(LocalTime.of(11, 0));
        Vote todaysVote = voteRepository.getUserVoteByDate(authUser.id(), LocalDate.now())
                .orElse(null);
        if (!mutable) {
            return new ResponseEntity<>(todaysVote, HttpStatus.METHOD_NOT_ALLOWED);
        }
        User user = userRepository.findById(authUser.id())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Restaurant restaurant = restaurantRepository.get(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        Vote newVote = new Vote(LocalDate.now(), user, restaurant);

        if (todaysVote != null) {
            newVote.setId(todaysVote.id());
        }

        Vote saved = voteRepository.save(newVote);

        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
}
