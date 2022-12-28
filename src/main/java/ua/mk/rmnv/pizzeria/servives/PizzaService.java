package ua.mk.rmnv.pizzeria.servives;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mk.rmnv.pizzeria.entities.Pizza;
import ua.mk.rmnv.pizzeria.repositories.PizzaRepository;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    public Pizza findById(Integer id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public void savePizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    public void deletePizza(Integer id) {
        pizzaRepository.deleteById(id);
    }

    public List<Pizza> sortByName() {
        List<Pizza> pizzaList = pizzaRepository.findAll();
        pizzaList.sort(Comparator.comparing(Pizza::getName));
        return pizzaList;
    }

    public List<Pizza> sortByWeight() {
        List<Pizza> pizzaList = pizzaRepository.findAll();
        pizzaList.sort(Comparator.comparing(Pizza::getWeight));
        return pizzaList;
    }

    public List<Pizza> sortByPrice() {
        List<Pizza> pizzaList = pizzaRepository.findAll();
        pizzaList.sort(Comparator.comparing(Pizza::getPrice));
        return pizzaList;
    }
}
