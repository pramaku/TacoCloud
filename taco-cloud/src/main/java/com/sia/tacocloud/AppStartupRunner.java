package com.sia.tacocloud;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sia.tacocloud.data.jpa.IngredientRepository;
import com.sia.tacocloud.models.Ingredient;
import com.sia.tacocloud.models.Ingredient.Type;;

@Component
public class AppStartupRunner implements ApplicationRunner {
	@Autowired
	IngredientRepository ingredientRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
/*		// save the initial data to the database.

		List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), 
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

		ingredientRepo.saveAll(ingredients);*/
	}

}
