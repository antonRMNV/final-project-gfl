package ua.mk.rmnv.pizzeria.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.mk.rmnv.pizzeria.entities.Basket;
import ua.mk.rmnv.pizzeria.entities.Drink;
import ua.mk.rmnv.pizzeria.entities.OrdersArchive;
import ua.mk.rmnv.pizzeria.entities.Pizza;
import ua.mk.rmnv.pizzeria.repositories.OrdersArchiveRepository;
import ua.mk.rmnv.pizzeria.servives.BasketService;
import ua.mk.rmnv.pizzeria.servives.DrinkService;
import ua.mk.rmnv.pizzeria.servives.PizzaService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Controller
@AllArgsConstructor
public class PizzeriaController {

    private final PizzaService pizzaService;
    private final DrinkService drinkService;
    private final BasketService basketService;

    private final OrdersArchiveRepository ordersArchiveRepository;

    @GetMapping("/")
    public String mainPage() {
        basketService.deleteAll();
        return "index";
    }

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

    @GetMapping("/drink-create")
    public String createDrinkForm(Drink drink) {
        return "drink-create";
    }

    @PostMapping("/drink-create")
    public String createDrink(Drink drink) {
        drinkService.saveDrink(drink);
        return "redirect:/drinks-adm";
    }

    @GetMapping("/pizza-delete/{id}")
    public String deletePizza(@PathVariable("id") Integer id) {
        pizzaService.deletePizza(id);
        return "redirect:/pizzas-adm";
    }

    @GetMapping("/drink-delete/{id}")
    public String deleteDrink(@PathVariable("id") Integer id) {
        drinkService.deleteDrink(id);
        return "redirect:/drinks-adm";
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

    @GetMapping("/drink-update/{id}")
    public String updateDrinkForm(@PathVariable("id") Integer id, Model model) {
        Drink drink = drinkService.findById(id);
        model.addAttribute("drink", drink);
        return "/drink-update";
    }

    @PostMapping("/drink-update")
    public String updateDrink(Drink drink) {
        drinkService.saveDrink(drink);
        return "redirect:/drinks-adm";
    }

    @GetMapping("/add-pizza-in-basket/{id}")
    public String addPizzaInBasket(@PathVariable("id") Integer id) {
        Basket basket = new Basket();
        Pizza pizza = pizzaService.findById(id);
        basket.setProductId(pizza.getId());
        basket.setProductName(pizza.getName());
        basket.setProductUrl(pizza.getUrl());
        basket.setProductPrice(pizza.getPrice());
        basketService.saveBasket(basket);
        return "redirect:/pizzas";
    }

    @GetMapping("/add-drink-in-basket/{id}")
    public String addDrinkInBasket(@PathVariable("id") Integer id) {
        Basket basket = new Basket();
        Drink drink = drinkService.findById(id);
        basket.setProductId(drink.getId());
        basket.setProductName(drink.getName());
        basket.setProductUrl(drink.getUrl());
        basket.setProductPrice(drink.getPrice());
        basketService.saveBasket(basket);
        return "redirect:/drinks";
    }

    @PostMapping("/filtered-pizzas")
    public String searchPizzaByNameOrComponents(Model model, @RequestParam("keyword") String keyword) {
        List<Pizza> pizzas = pizzaService.findByNameOrComponents(keyword);
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("keyword", keyword);
        if(keyword.isEmpty()) {
            return "redirect:/pizzas";
        }
        return "pizza-list";
    }

    @PostMapping("/filtered-drinks")
    public String searchDrinkByNameOrDescription(Model model, @RequestParam("keyword") String keyword) {
        List<Drink> drinks = drinkService.findByNameOrDescription(keyword);
        model.addAttribute("drinks", drinks);
        model.addAttribute("keyword", keyword);
        if(keyword.isEmpty()) {
            return "redirect:/drinks";
        }
        return "drink-list";
    }

    @GetMapping("/basket-list")
    public String basketPage(Model model) {
        List<Basket> baskets = basketService.findAll();
        baskets.sort(Comparator.comparing(Basket::getProductName));
        int orderSum = 0;
        for(Basket b : baskets) {
            orderSum += b.getProductPrice();
        }
        model.addAttribute("baskets", baskets);
        model.addAttribute("orderSum", orderSum);
        return "basket";
    }

    @GetMapping("/delete-from-basket/{id}")
    public String deleteOrderFromBasket(@PathVariable("id") Integer id) {
        basketService.deleteBasket(id);
        return "redirect:/basket-list";
    }

    @GetMapping("/checkout-page")
    public String checkoutPageForm() {
        return "checkout";
    }

    @PostMapping("/save-order")
    public String checkoutPage(@RequestParam String customerName, @RequestParam String customerSurname,
                               @RequestParam String customerPhonenumber, @RequestParam String customerAddress) {
        List<Basket> baskets = basketService.findAll();
        for(Basket b : baskets) {
            OrdersArchive ordersArchive = new OrdersArchive();
            ordersArchive.setOrderTime(String.valueOf(LocalDateTime.now()));
            ordersArchive.setProductName(b.getProductName());
            ordersArchive.setCustomerName(customerName);
            ordersArchive.setCustomerSurname(customerSurname);
            ordersArchive.setCustomerPhonenumber(customerPhonenumber);
            ordersArchive.setCustomerAddress(customerAddress);
            ordersArchiveRepository.save(ordersArchive);
        }
        basketService.deleteAll();
        return "redirect:/main-page";
    }
}
