package com.hmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmdy.recipe.domain.Ingredient;
import com.hmdy.recipe.domain.Recipe;
import com.hmdy.recipe.domain.UnitOfMeasurement;
import com.hmdy.recipe.repository.IngredientRepository;
import com.hmdy.recipe.repository.RecipeRepository;
import com.hmdy.recipe.repository.UnitOfMeasurementRepository;
import com.hmdy.recipe.service.IngredientService;


@Service
public class IngredientServiceImpl implements IngredientService{

	private final IngredientRepository ingredientRepository;
	
	private final UnitOfMeasurementRepository uomRepository;
	
	private final RecipeRepository recipeRepository;
	
	public IngredientServiceImpl(IngredientRepository ingredientRepository, UnitOfMeasurementRepository uomRepository, RecipeRepository recipeRepository) {
		super();
		this.ingredientRepository = ingredientRepository;
		this.uomRepository = uomRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Ingredient createIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return ingredientRepository.save(ingredient);
	}

	@Override
	public List<Ingredient> getAllIngredient() {
		// TODO Auto-generated method stub
		return (List<Ingredient>) ingredientRepository.findAll();
	}

	@Override
	public Optional<Ingredient> getIngredientById(Long id) {
		// TODO Auto-generated method stub
		return ingredientRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteIngredientById(Long id) {
		Optional<Ingredient> ingredientOpt= getIngredientById(id);
		if(ingredientOpt.isEmpty()) throw new RuntimeException("Ingredient is empty");
		
		Ingredient ingredient=ingredientOpt.get();
		ingredient.setRecipe(null);
		ingredient.setUnitOfMeasurement(null);
		saveIngredient(ingredient);
		// TODO Auto-generated method stub
		ingredientRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Ingredient createIngredient(Ingredient ingredient, Long recipeId, Long uomId) {
		// TODO Auto-generated method stub
		Optional<UnitOfMeasurement> uomOpt=uomRepository.findById(uomId);
		Optional<Recipe> recipeOpt=recipeRepository.findById(recipeId);
		
		if(uomOpt.isEmpty()) throw new RuntimeException("uom is not present");
		if(recipeOpt.isEmpty()) throw new RuntimeException("recipe is not present");
		
		//ingredient -uom (oneway)
		ingredient.setUnitOfMeasurement(uomOpt.get());
		
		Recipe recipe=recipeOpt.get();
		recipe.getIngredients().add(ingredient);
		
		ingredient.setRecipe(recipe);
		
		recipeRepository.save(recipe);
		
		Optional<Ingredient> savedIngredient=ingredientRepository.findByDescription(ingredient.getDescription());
		
		if(savedIngredient.isEmpty()) throw new RuntimeException("Ingredient is not found");
//		ingredientRepository.save(ingredient);
		return savedIngredient.get();
	}

	@Override
	public Ingredient updateIngredient(Ingredient ingredient, Long recipeId, Long uomId) {
		// TODO Auto-generated method stub
				Optional<UnitOfMeasurement> uomOpt=uomRepository.findById(uomId);
				Optional<Recipe> recipeOpt=recipeRepository.findById(recipeId);
				
				if(uomOpt.isEmpty()) throw new RuntimeException("uom is not present");
				if(recipeOpt.isEmpty()) throw new RuntimeException("recipe is not present");
				
				//ingredient -uom (oneway)
				ingredient.setUnitOfMeasurement(uomOpt.get());
				
				
				ingredient.setRecipe(recipeOpt.get());
				
				return ingredientRepository.save(ingredient);
	}

	@Override
	public Ingredient saveIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return ingredientRepository.save(ingredient);
	}

}
