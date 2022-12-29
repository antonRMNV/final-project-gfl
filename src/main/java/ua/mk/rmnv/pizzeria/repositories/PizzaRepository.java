package ua.mk.rmnv.pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.mk.rmnv.pizzeria.entities.Pizza;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    @Query("SELECT p FROM Pizza p WHERE p.name LIKE %?1% OR p.components LIKE %?1%")
    List<Pizza> searchByName(String keyword);
}
