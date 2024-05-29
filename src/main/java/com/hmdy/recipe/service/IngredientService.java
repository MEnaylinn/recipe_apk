package com.hmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hmdy.recipe.domain.Ingredient;
public interface IngredientService {
	
	Ingredient createIngredient(Ingredient ingredient,Long recipeId,Long uomId);
	Ingredient updateIngredient(Ingredient ingredient,Long recipeId,Long uomId);
	Ingredient saveIngredient(Ingredient ingredient);
	
	List<Ingredient> getAllIngredient();
	
	Optional<Ingredient> getIngredientById(Long id);
	
	void deleteIngredientById(Long id);

	Ingredient createIngredient(Ingredient ingredient);
}
