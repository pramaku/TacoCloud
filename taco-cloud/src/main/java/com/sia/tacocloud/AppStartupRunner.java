package com.sia.tacocloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.sia.tacocloud.data.jpa.IngredientRepository;;

@Component
@Profile("web")
public class AppStartupRunner implements ApplicationRunner {
	Logger log = LoggerFactory.getLogger(AppStartupRunner.class);
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
		log.info("App starter :: run");
	}

}
