package ua.mk.rmnv.pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.mk.rmnv.pizzeria.entities.Drink;
import ua.mk.rmnv.pizzeria.entities.Pizza;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {

    @Query("SELECT d FROM Drink d WHERE d.name LIKE %?1% OR d.description LIKE %?1%")
    List<Drink> searchByNameOrDescription(String keyword);
}
