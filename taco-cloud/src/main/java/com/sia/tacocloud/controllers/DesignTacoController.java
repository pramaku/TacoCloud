package com.sia.tacocloud.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
@Profile("web")
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
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order, Model model)
    {
        if (errors.hasErrors())
        {
            List<Ingredient> ingredients = getAllIngredients();
            for (Type type : Ingredient.Type.values())
            {
                model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
            }
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
        return ingredients;
    }
}
