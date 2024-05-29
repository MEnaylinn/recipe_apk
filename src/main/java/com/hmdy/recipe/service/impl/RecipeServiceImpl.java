package com.hmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hmdy.recipe.domain.Category;
import com.hmdy.recipe.domain.Recipe;
import com.hmdy.recipe.repository.CategoryRepository;
import com.hmdy.recipe.repository.RecipeRepository;
import com.hmdy.recipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{
	
	private final RecipeRepository recipeRepository;
	private final CategoryRepository categoryRepository;
	
	

	

	public RecipeServiceImpl(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Recipe createRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		Set<Category> categories=recipe.getCategories();
		categories.forEach(c -> c.getRecipes().add(recipe));
		categoryRepository.saveAll(categories);
		
		return recipeRepository.save(recipe);
	}

	@Override
	public List<Recipe> getAllRecipe() {
		// TODO Auto-generated method stub
		return (List<Recipe>) recipeRepository.findAll();
	}

	@Override
	public Optional<Recipe> getRecipeById(Long id) {
		// TODO Auto-generated method stub
		return recipeRepository.findById(id);
	}

	@Override
	public void deleteRecipeById(Long id) {
		// TODO Auto-generated method stub
		recipeRepository.deleteById(id);
	}

}
