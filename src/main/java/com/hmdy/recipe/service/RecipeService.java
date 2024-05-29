package com.hmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hmdy.recipe.domain.Recipe;


public interface RecipeService {
	
	Recipe createRecipe(Recipe recipe);
	List<Recipe> getAllRecipe();
	Optional<Recipe> getRecipeById(Long id);
	void deleteRecipeById(Long id);

}
