package com.hmdy.recipe.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.hmdy.recipe.domain.Category;
import com.hmdy.recipe.domain.Notes;
import com.hmdy.recipe.domain.Recipe;
import com.hmdy.recipe.service.CategoryService;
import com.hmdy.recipe.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	private final RecipeService recipeService;
	private final CategoryService categoryService;

	public RecipeController(RecipeService recipeService, CategoryService categoryService) {
		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;
	}

	@GetMapping("/show/{id}")
	public String showRecipeDetails(@PathVariable Long id, Model model) {
		Optional<Recipe> recipeOpt = recipeService.getRecipeById(id);

		if (recipeOpt.isEmpty()) {
			throw new RuntimeException("Recipe not found");
		}

		model.addAttribute("recipe", recipeOpt.get());
		return "recipe/recipe-details";

	}

	@GetMapping("/{id}/ingredients")
	public String showIngredientbyRecipe(@PathVariable Long id, Model model) {
		Optional<Recipe> recipeOpt = recipeService.getRecipeById(id);

		if (recipeOpt.isEmpty()) {
			throw new RuntimeException("Recipe not found");
		}
		model.addAttribute("recipeId",id);
		model.addAttribute("ingredients", recipeOpt.get().getIngredients());
		return "ingredient/show";
	}

	@GetMapping("/new")
	public String showRecipeForm(Model model) {
		Notes note = new Notes();
		Recipe recipe = new Recipe();
		recipe.setNote(note);

		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("recipe", recipe);
		model.addAttribute("categories", categories);

		return "recipe/add-recipe";
	}

	@PostMapping("/new")
	public String createRecipe(@ModelAttribute Recipe recipe, @RequestParam String[] categoryNames) {
		if (categoryNames == null) {
				throw new RuntimeException("categoryNames is null");
		}
		Set<Category> categories=new HashSet<>();
		
		for(final String categoryName : categoryNames) {
			Optional<Category> categoryOpt=categoryService.getCategoryByName(categoryName);
			
			if(categoryOpt.isPresent()) {
				categories.add(categoryOpt.get());
			}
		}
		
		recipe.setCategories(categories);
		
		Recipe createRecipe = recipeService.createRecipe(recipe);
		
		System.out.println(createRecipe);
		return "redirect:/";


}
	@GetMapping("/update/{id}")
	public String showRecipeUpdateForm(@PathVariable Long id,Model model) {
		
		Optional<Recipe> recipeOpt=recipeService.getRecipeById(id);
		
		if(recipeOpt.isEmpty()) {
			throw new RuntimeException("Recipe is not found");
		}
		
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("recipe", recipeOpt.get());
		model.addAttribute("categories", categories);
		return "recipe/add-recipe";
	}
	@GetMapping("/delete/{id}")
	public String deleteRecipe(@PathVariable Long id) {
		Optional<Recipe> recipeOpt = recipeService.getRecipeById(id);

		if (recipeOpt.isEmpty()) {
			throw new RuntimeException("Recipe not found");
		}
		Recipe recipe=recipeOpt.get();
		recipe.getCategories().clear();
		
		recipeService.deleteRecipeById(id);
		return "redirect:/";
		
	
	}

}
