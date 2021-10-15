package ru.javaops.bootjava.web;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@AllArgsConstructor
@Slf4j
public class AdminRestaurantController {

    private final RestaurantRepository restaurantRepository;

    @GetMapping("{id}")
    public Restaurant get(@PathVariable("id") int id) throws NotFoundException {
        log.info("get restaurant {}", id);
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete restaurant {}", id);
        restaurantRepository.deleteById(id);
    }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        return restaurantRepository.save(restaurant);
    }

    @PutMapping("{id}")
    public Restaurant update(@RequestBody Restaurant restaurant,
                             @PathVariable("id") int id) {
        log.info("update restaurant {}", id);
        restaurant.setId(id);
        return restaurantRepository.save(restaurant);
    }

}
