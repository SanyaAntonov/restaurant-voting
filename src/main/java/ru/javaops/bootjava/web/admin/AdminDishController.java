package ru.javaops.bootjava.web.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant/{restId}/dish")
@AllArgsConstructor
@Slf4j
@Tag(name = "Admin Dish Controller")
public class AdminDishController {
    private final DishRepository dishRepository;
    private final RestaurantRepository restRepository;

    @GetMapping
    public ResponseEntity<List<Dish>> getAll(@PathVariable("restId") int id) {
        log.info("get all dishes");
        List<Dish> all = dishRepository.getAll(id);
        if (all == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Dish> getById(@PathVariable("restId") int restId,
                                        @PathVariable("id") int id) {
        Dish dish = dishRepository.get(restId, id);
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("restId") int restId,
                       @PathVariable("id") int id) {

        Dish dish = dishRepository.get(restId, id);
        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dishRepository.delete(dish);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dish> create(@RequestBody Dish dish,
                                       @PathVariable("restId") int restId) {
        Restaurant restaurant = restRepository.findById(restId).orElse(null);
        if (restaurant == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dish.setRestaurant(restaurant);
        Dish created = dishRepository.save(dish);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Dish> update (@RequestBody Dish newDish,
                        @PathVariable("restId") int restId,
                        @PathVariable("id") int id) {
        Dish oldDish = dishRepository.get(restId, id);
        if (oldDish == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Restaurant restaurant = restRepository.findById(restId).orElse(null);
        if (restaurant == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        newDish.setRestaurant(restaurant);
        newDish.setId(oldDish.id());
        Dish update = dishRepository.save(newDish);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
}
