package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> getAllByRestaurantIdOrderByPrice(Integer restaurantId);

    Dish getByIdAndRestaurantId(Integer id, Integer restaurantId);

}
