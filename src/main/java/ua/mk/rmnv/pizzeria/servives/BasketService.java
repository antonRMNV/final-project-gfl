package ua.mk.rmnv.pizzeria.servives;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mk.rmnv.pizzeria.entities.Basket;
import ua.mk.rmnv.pizzeria.repositories.BasketRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;

    public Basket findById(Integer id) {
        return basketRepository.findById(id).orElse(null);
    }

    public List<Basket> findAll() {
        return basketRepository.findAll();
    }

    public void saveBasket(Basket basket) {
        basketRepository.save(basket);
    }

    public void deleteBasket(Integer id) {
        basketRepository.deleteById(id);
    }

    public void deleteAll() {
        basketRepository.deleteAll();
    }

}
