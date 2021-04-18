package ru.javaops.bootjava.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@AllArgsConstructor
@Slf4j
@Tag(name = "Admin Restaurant Controller")
public class AdminRestaurantController {

    private final RestaurantRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> get(@PathVariable("id") int id) {
        log.info("get restaurant {}", id);
        Restaurant found = repository.get(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        log.info("get all restaurants");
        List<Restaurant> all = repository.getAll()
                .orElseThrow(() -> new NotFoundException("Restaurants not found"));

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        Restaurant created = repository.save(restaurant);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant,
                                             @PathVariable("id") int id) {
        log.info("update restaurant {}", id);
        restaurant.setId(id);
        Restaurant updated = repository.save(restaurant);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
