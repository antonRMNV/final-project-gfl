package ua.mk.rmnv.pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mk.rmnv.pizzeria.entities.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
}
