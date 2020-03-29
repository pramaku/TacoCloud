package com.sia.tacocloud.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sia.tacocloud.data.jpa.IngredientRepository;
import com.sia.tacocloud.data.jpa.TacoRepository;
import com.sia.tacocloud.models.Ingredient;
import com.sia.tacocloud.models.Ingredient.Type;
import com.sia.tacocloud.models.Order;
import com.sia.tacocloud.models.Taco;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController
{
    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
    private IngredientRepository ingredientRepo;
    private TacoRepository tacoRepository;

    @ModelAttribute("order")
    public Order order()
    {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
      return new Taco();
    }
    
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepository)
    {
        super();
        this.ingredientRepo = ingredientRepo;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model)
    {
        log.info("showDesignForm .. request with model " + model);
        List<Ingredient> ingredients = getAllIngredients();
        for (Type type : Ingredient.Type.values())
        {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order)
    {
        if (errors.hasErrors())
        {
            return "design";
        }
        log.info("processDesign .. request to create design " + design);
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        //attributes.addFlashAttribute("order", order);
        return "redirect:/orders/current";
    }

    /**
     * @param ingredients
     * @param type
     * @return
     */
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type)
    {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    private List<Ingredient> getAllIngredients()
    {
        Iterable<Ingredient> ingredientsIt = ingredientRepo.findAll();

        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient in: ingredientsIt)
        {
            ingredients.add(in);
        }
        /*
         * List<Ingredient> ingredients = Arrays.asList(
         * new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
         * new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
         * new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
         * new Ingredient("CARN", "Carnitas", Type.PROTEIN),
         * new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
         * new Ingredient("LETC", "Lettuce", Type.VEGGIES),
         * new Ingredient("CHED", "Cheddar", Type.CHEESE),
         * new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
         * new Ingredient("SLSA", "Salsa", Type.SAUCE),
         * new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
         * );
         */
        return ingredients;
    }
}
