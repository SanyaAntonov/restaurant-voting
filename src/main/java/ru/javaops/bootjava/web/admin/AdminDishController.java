package ru.javaops.bootjava.web.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
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
        log.info("get all dishes by restaurant");
        List<Dish> all = dishRepository.getAllByRestaurant(id)
                .orElseThrow(()-> new NotFoundException("Dishes not found"));

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Dish> getById(@PathVariable("restId") int restId,
                                        @PathVariable("id") int id) {
        log.info("get dish {}", id);
        Dish dish = dishRepository.get(restId, id)
                .orElseThrow(()-> new NotFoundException("Dish not found"));

        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("restId") int restId,
                       @PathVariable("id") int id) {
        log.info("delete dish {}", id);
        Dish dish = dishRepository.get(restId, id)
                .orElseThrow(()-> new NotFoundException("Dish not found"));

        dishRepository.delete(dish);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dish> create(@RequestBody Dish dish,
                                       @PathVariable("restId") int restId) {
        log.info("create dish {}", dish);
        Restaurant restaurant = restRepository.findById(restId)
                .orElseThrow(()-> new NotFoundException("Restaurant not found"));

        dish.setRestaurant(restaurant);
        Dish created = dishRepository.save(dish);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Dish> update (@RequestBody Dish newDish,
                        @PathVariable("restId") int restId,
                        @PathVariable("id") int id) {
        log.info("update dish {}", id);
        Dish oldDish = dishRepository.get(restId, id)
                .orElseThrow(()-> new NotFoundException("Dish not found"));

        Restaurant restaurant = restRepository.findById(restId)
                .orElseThrow(()-> new NotFoundException("Restaurant not found"));

        newDish.setRestaurant(restaurant);
        newDish.setId(oldDish.id());
        Dish update = dishRepository.save(newDish);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
}
