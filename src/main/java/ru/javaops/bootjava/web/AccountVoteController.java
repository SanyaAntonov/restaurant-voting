package ru.javaops.bootjava.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.AuthUser;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
@Slf4j
public class AccountVoteController {
    private final VoteRepository voteRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;


    @GetMapping("/vote")
    public List<Restaurant> getAllRestaurants() {
        log.info("get Restaurants to vote");
        return restaurantRepository.findAll();
    }

    @GetMapping("/vote/{restId}")
    public List<Dish> getAllRestaurantDishes(@PathVariable("restId") int id) {
        log.info("get dish menu for restaurant {}", id);
        return dishRepository.getAllByRestaurantIdOrderByPrice(id);
    }

    @GetMapping("/history")
    public List<Vote> getVotingHistory(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get voting history for user{}", authUser.getUser());
        return voteRepository.getVoteByUserOrderByDateDesc(authUser.getUser());
    }

    @GetMapping("/history/{voteId}")
    public Vote getVote(@AuthenticationPrincipal AuthUser authUser,
                        @PathVariable("voteId") int id) {
        log.info("get vote in history {}", id);
        return voteRepository.getVoteByUserAndId(authUser.getUser(), id);
    }

    @PostMapping("/vote/{restId}")
    @Transactional
    public ResponseEntity<Vote> createOrUpdateVote(@AuthenticationPrincipal AuthUser authUser,
                                                   @PathVariable("restId") int id) {
        log.info("create or update vote {}", id);
        // If it is after 11:00 then it is too late, vote can't be changed
        if (!LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        Vote todaysVote = voteRepository.getVoteByUserAndDate(authUser.getUser(), LocalDate.now());
        Vote vote = new Vote();

        if (todaysVote != null) {
            vote.setId(todaysVote.getId());
        }
        vote.setDate(LocalDate.now());
        vote.setUser(userRepository.getOne(id));
        vote.setRestaurant(restaurantRepository.getOne(id));
        return new ResponseEntity<>(voteRepository.save(vote), HttpStatus.OK);
    }
}
