package ru.javaops.bootjava.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant/{restId}/dish")
@AllArgsConstructor
@Slf4j
public class AdminDishController {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Dish> getAll(@PathVariable("restId") int id) {
        log.info("get all dishes by restaurant");
        return dishRepository.getAllByRestaurantIdOrderByPrice(id);
    }

    @GetMapping("{id}")
    public Dish getById(@PathVariable("restId") int restId,
                        @PathVariable("id") int id) {
        log.info("get dish {}", id);
        return dishRepository.getByIdAndRestaurantId(id, restId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("restId") int restId,
                       @PathVariable("id") int id) {
        log.info("delete dish {}", id);
        dishRepository.deleteById(id);
    }

    @PostMapping
    @Transactional
    public Dish create(@RequestBody Dish dish,
                       @PathVariable("restId") int restId) {
        log.info("create dish {}", dish);
        dish.setRestaurant(restaurantRepository.getOne(restId));
        return dishRepository.save(dish);
    }

    @PutMapping("{id}")
    @Transactional
    public Dish update(@RequestBody Dish newDish,
                       @PathVariable("restId") int restId,
                       @PathVariable("id") int id) {
        log.info("update dish {}", id);
        newDish.setRestaurant(restaurantRepository.getOne(restId));
        newDish.setId(id);
        return dishRepository.save(newDish);
    }
}
