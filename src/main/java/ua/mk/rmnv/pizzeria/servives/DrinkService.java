package ua.mk.rmnv.pizzeria.servives;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mk.rmnv.pizzeria.entities.Drink;
import ua.mk.rmnv.pizzeria.repositories.DrinkRepository;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public Drink findById(Integer id) {
        return drinkRepository.findById(id).orElse(null);
    }

    public List<Drink> findAll() {
        return drinkRepository.findAll();
    }

    public void saveDrink(Drink drink) {
        drinkRepository.save(drink);
    }

    public void deleteDrink(Integer id) {
        drinkRepository.deleteById(id);
    }

    public List<Drink> sortByName() {
        List<Drink> drinkList = drinkRepository.findAll();
        drinkList.sort(Comparator.comparing(Drink::getName));
        return drinkList;
    }

    public List<Drink> sortByVolume() {
        List<Drink> drinkList = drinkRepository.findAll();
        drinkList.sort(Comparator.comparing(Drink::getVolume));
        return drinkList;
    }

    public List<Drink> sortByPrice() {
        List<Drink> drinkList = drinkRepository.findAll();
        drinkList.sort(Comparator.comparing(Drink::getPrice));
        return drinkList;
    }
}
