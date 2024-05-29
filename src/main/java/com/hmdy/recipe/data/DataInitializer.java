package com.hmdy.recipe.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hmdy.recipe.domain.Category;
import com.hmdy.recipe.domain.Difficulty;
import com.hmdy.recipe.domain.Ingredient;
import com.hmdy.recipe.domain.Notes;
import com.hmdy.recipe.domain.Recipe;
import com.hmdy.recipe.domain.UnitOfMeasurement;
import com.hmdy.recipe.repository.CategoryRepository;
import com.hmdy.recipe.repository.IngredientRepository;
import com.hmdy.recipe.repository.RecipeRepository;
import com.hmdy.recipe.repository.UnitOfMeasurementRepository;

// add data to inilize
@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasurementRepository uomRepository;

	public DataInitializer(CategoryRepository categoryRepository,
			RecipeRepository recipeRepository, UnitOfMeasurementRepository uomRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
		recipeRepository.saveAll(getRecipes());
		
	}
	
	List<Recipe> getRecipes(){
		List<Recipe> recipes = new ArrayList<>();
		
		Optional<Category> europeanOpt = categoryRepository.findByName("European");

		if (europeanOpt.isEmpty()) {
			throw new RuntimeException("European category is not found");
		}

		Optional<Category> itelianOpt = categoryRepository.findByName("Italian");

		if (itelianOpt.isEmpty()) {
			throw new RuntimeException("Italian category is not found");
		}

		Optional<UnitOfMeasurement> cupOpt = uomRepository.findByName("cup");

		if (cupOpt.isEmpty()) {
			throw new RuntimeException("cup uom is not found");
		}

		Optional<UnitOfMeasurement> teaspoonOpt = uomRepository.findByName("teaspoon");

		if (teaspoonOpt.isEmpty()) {
			throw new RuntimeException("teaspoon uom is not found");
		}

		Optional<UnitOfMeasurement> tablespoonOpt = uomRepository.findByName("tablespoon");

		if (tablespoonOpt.isEmpty()) {
			throw new RuntimeException("tablespoon uom is not found");
		}

		Optional<UnitOfMeasurement> ounceOpt = uomRepository.findByName("ounce");

		if (ounceOpt.isEmpty()) {
			throw new RuntimeException("ounce uom is not found");
		}

		Optional<UnitOfMeasurement> poundOpt = uomRepository.findByName("pound");

		if (poundOpt.isEmpty()) {
			throw new RuntimeException("pound uom is not found");
		}
		
		Recipe recipe = new Recipe(
				"Salmon Cobb Salad", 
				"Salty, yummy, refreshing, and light. The dill adds an unexpected, amazing flavor. It's addicting! Lots of great protein in this one, with the eggs and salmon.", 
				30, 
				0, 
				"https://www.allrecipes.com/recipe/258277/salmon-cobb-salad/", 
				10,
				"Step 1. \r\n"
				+ "Whisk buttermilk, mayonnaise, shallot, dill, lemon juice, and salt together in a bowl until dressing is smooth; refrigerate.\r\n"
				+ "\r\n"
				+ "Step 2. \r\n"
				+ "Mix lettuce, bacon, avocado, eggs, and salmon together in a bowl. Add dressing and toss until coated; season with black pepper.", 
				Difficulty.EASY);
		
		UnitOfMeasurement cup = cupOpt.get();
		UnitOfMeasurement teaspoon = teaspoonOpt.get();
		UnitOfMeasurement tablespoon = tablespoonOpt.get();
		UnitOfMeasurement pound = poundOpt.get();
		UnitOfMeasurement ounce = ounceOpt.get();
		
		Ingredient buttermilk = new Ingredient("buttermilk",new BigDecimal(3/4),recipe,cup);
		Ingredient mayonnaise = new Ingredient("mayonnaise",new BigDecimal(1/2),recipe,cup);
		Ingredient mincedShallot = new Ingredient("minced shallot",new BigDecimal(1/4),recipe,cup);
		Ingredient choppedFreshDill = new Ingredient("chopped fresh dill",new BigDecimal(3),recipe, tablespoon);
		Ingredient lemonJuice = new Ingredient("lemon juice",new BigDecimal(1),recipe, teaspoon);
		Ingredient salt = new Ingredient("salt",new BigDecimal(1/2),recipe, cupOpt.get());
		Ingredient lettue = new Ingredient("Bibb lettuce, leaves separated",new BigDecimal(1),recipe, pound);
		Ingredient bacon = new Ingredient("cooked bacon, cut into pieces",new BigDecimal(8),recipe, ounce);
		Ingredient avogado = new Ingredient("avocado, cut into 1/2-inch pieces",new BigDecimal(1),recipe, pound);
		Ingredient egg = new Ingredient("hard-boiled eggs, quartered and halved",new BigDecimal(3),recipe, pound);
		Ingredient salmon = new Ingredient("package smoked salmon",new BigDecimal(3),recipe, ounce);
		Ingredient blackPepper = new Ingredient("ground black pepper to taste",new BigDecimal(1),recipe, ounce);
		
		Notes note = new Notes("Please serve with clean plates. and bla bla bla.Please serve with clean plates. and bla bla bla.");
	
		
		
		//Recipe-Note
		recipe.setNote(note);
		note.setRecipe(recipe);
		
		//recipe - category
		Category european = europeanOpt.get();
		Category itellian = itelianOpt.get();
		recipe.getCategories().add(european);
		recipe.getCategories().add(itellian);
		european.getRecipes().add(recipe);
		itellian.getRecipes().add(recipe);
		
		categoryRepository.save(european);
		categoryRepository.save(itellian);
		
		//recipe - ingredients
		recipe.getIngredients().add(blackPepper);
		recipe.getIngredients().add(avogado);
		recipe.getIngredients().add(bacon);
		recipe.getIngredients().add(buttermilk);
		recipe.getIngredients().add(choppedFreshDill);
		recipe.getIngredients().add(egg);
		recipe.getIngredients().add(lemonJuice);
		recipe.getIngredients().add(lettue);
		recipe.getIngredients().add(mayonnaise);
		recipe.getIngredients().add(mincedShallot);
		recipe.getIngredients().add(salmon);
		recipe.getIngredients().add(salt);
		
		recipes.add(recipe);
		return recipes;
	}
}
