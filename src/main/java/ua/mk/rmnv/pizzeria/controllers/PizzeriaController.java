package ua.mk.rmnv.pizzeria.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.mk.rmnv.pizzeria.entities.Drink;
import ua.mk.rmnv.pizzeria.entities.Pizza;
import ua.mk.rmnv.pizzeria.servives.DrinkService;
import ua.mk.rmnv.pizzeria.servives.PizzaService;

import java.util.Comparator;
import java.util.List;

@Controller
@AllArgsConstructor
public class PizzeriaController {

    private final PizzaService pizzaService;
    private final DrinkService drinkService;

    @GetMapping("/pizzas")
    public String viewPizzaList(Model model) {
        List<Pizza> pizzas = pizzaService.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizza-list";
    }

    @GetMapping("/drinks")
    public String viewDrinksList(Model model) {
        List<Drink> drinks = drinkService.findAll();
        model.addAttribute("drinks", drinks);
        return "drink-list";
    }

    @GetMapping("/main-page")
    public String backToMainPage() {
        return "index";
    }

    @GetMapping("/pizzas-adm")
    public String goToPizzaListAdmPanel(Model model) {
        List<Pizza> pizzas = pizzaService.findAll();
        pizzas.sort(Comparator.comparing(Pizza::getName));
        model.addAttribute("pizzas", pizzas);
        return "pizza-list-adm";
    }

    @GetMapping("/drinks-adm")
    public String goToDrinkListAdmPanel(Model model) {
        List<Drink> drinks = drinkService.findAll();
        drinks.sort(Comparator.comparing(Drink::getName));
        model.addAttribute("drinks", drinks);
        return "drink-list-adm";
    }

    @GetMapping("/sort-pizza-by-name")
    public String sortPizzaByName(Model model) {
        List<Pizza> pizzas = pizzaService.sortByName();
        model.addAttribute("pizzas", pizzas);
        return "pizza-list";
    }

    @GetMapping("/sort-pizza-by-weight")
    public String sortPizzaByWeight(Model model) {
        List<Pizza> pizzas = pizzaService.sortByWeight();
        model.addAttribute("pizzas", pizzas);
        return "pizza-list";
    }

    @GetMapping("/sort-pizza-by-price")
    public String sortPizzaByPrice(Model model) {
        List<Pizza> pizzas = pizzaService.sortByPrice();
        model.addAttribute("pizzas", pizzas);
        return "pizza-list";
    }

    @GetMapping("/sort-drink-by-name")
    public String sortDrinkByName(Model model) {
        List<Drink> drinks = drinkService.sortByName();
        model.addAttribute("drinks", drinks);
        return "drink-list";
    }

    @GetMapping("/sort-drink-by-volume")
    public String sortDrinkByVolume(Model model) {
        List<Drink> drinks = drinkService.sortByVolume();
        model.addAttribute("drinks", drinks);
        return "drink-list";
    }

    @GetMapping("/sort-drink-by-price")
    public String sortDrinkByPrice(Model model) {
        List<Drink> drinks = drinkService.sortByPrice();
        model.addAttribute("drinks", drinks);
        return "drink-list";
    }

    @GetMapping("/pizza-create")
    public String createPizzaForm(Pizza pizza) {
        return "pizza-create";
    }

    @PostMapping("/pizza-create")
    public String createPizza(Pizza pizza) {
        pizzaService.savePizza(pizza);
        return "redirect:/pizzas-adm";
    }

    @GetMapping("/pizza-delete/{id}")
    public String deletePizza(@PathVariable("id") Integer id) {
        pizzaService.deletePizza(id);
        return "redirect:/pizzas-adm";
    }

    @GetMapping("/pizza-update/{id}")
    public String updatePizzaForm(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaService.findById(id);
        model.addAttribute("pizza", pizza);
        return "/pizza-update";
    }

    @PostMapping("/pizza-update")
    public String updatePizza(Pizza pizza) {
        pizzaService.savePizza(pizza);
        return "redirect:/pizzas-adm";
    }
}
