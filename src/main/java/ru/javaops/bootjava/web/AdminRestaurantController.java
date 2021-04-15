package ru.javaops.bootjava.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.util.ValidationUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@AllArgsConstructor
@Slf4j
@Tag(name = "Restaurant Controller")
public class AdminRestaurantController {

    private final RestaurantRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> get(@PathVariable("id") int id) {
        log.info("get restaurant {}", id);
        Restaurant found = repository.findById(id).orElse(null);
        if (found == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(found, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        log.info("get all restaurants");
        List<Restaurant> all = repository.getAll();
        if (all == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete restaurant {}", id);
        repository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        Restaurant created = repository.save(restaurant);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant,
                                             @PathVariable("id") int id) {
        log.info("update restaurant {}", id);
        restaurant.setId(id);
        Restaurant updated = repository.save(restaurant);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
