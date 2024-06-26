package com.hmdy.recipe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hmdy.recipe.domain.Recipe;
import com.hmdy.recipe.service.RecipeService;

@Controller
public class HomeController {
	
	private final RecipeService recipeService;

	
	public HomeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}


	@GetMapping({"","/","/index"})
	public String showAllRecipe(Model model) {
		List<Recipe> recipes=recipeService.getAllRecipe();
		model.addAttribute("recipes",recipes);
		return "index";
		
	}

}
