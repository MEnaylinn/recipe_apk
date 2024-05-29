package com.hmdy.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hmdy.recipe.domain.Ingredient;
import com.hmdy.recipe.domain.UnitOfMeasurement;
import com.hmdy.recipe.service.IngredientService;
import com.hmdy.recipe.service.RecipeService;
import com.hmdy.recipe.service.UomService;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
	
	private final IngredientService ingredientService;
	private final UomService uomService;

	public IngredientController(IngredientService ingredientService, UomService uomService) {
		super();
		this.ingredientService = ingredientService;
		this.uomService = uomService;
	}
	
	@GetMapping("/new/{id}")
	public String showAddIngredient(@PathVariable Long id, Model model) {
		List<UnitOfMeasurement> uomList=uomService.getAllUoms();
		
		Ingredient ingredient=new Ingredient();
		model.addAttribute("uomList",uomList);
		model.addAttribute("ingredient",ingredient);
		model.addAttribute("recipeId",id);
		
		return "ingredient/ingredient-form";
	}
	
	@PostMapping("/new")
	public String createIngredient(@RequestParam Long recipeId,@ModelAttribute Ingredient ingredient,@RequestParam Long uomId) {
		if(ingredient.getId() == null) {
			Ingredient createIngredient=ingredientService.createIngredient(ingredient, recipeId, uomId);			
		}else {
			Ingredient updateIngredient=ingredientService.updateIngredient(ingredient, recipeId, uomId);			

		}
		
		return "redirect:/recipe/"+recipeId+"/ingredients";
	}	
	
	@GetMapping("/update/{ingredientId}")
	public String showUpdateIngredient(@PathVariable Long ingredientId,Model model) {
		Optional<Ingredient> ingredientOpt=ingredientService.getIngredientById(ingredientId);
		if(ingredientOpt.isEmpty()) throw new RuntimeException("Ingredient is empty");
		
		Ingredient ingredient=ingredientOpt.get();
		model.addAttribute("ingredient",ingredient);
		model.addAttribute("recipeId",ingredient.getRecipe().getId());
		
		List<UnitOfMeasurement> uomList=uomService.getAllUoms();
		model.addAttribute("uomList",uomList);
		
		return "ingredient/ingredient-form";
	}
	
	@GetMapping("/delete/{recipeId}/{ingredientId}")
	@Transactional
	public String deleteIngredient(@PathVariable Long recipeId,@PathVariable Long ingredientId) {
		
		ingredientService.deleteIngredientById(ingredientId);
		return "redirect:/recipe/"+recipeId+"/ingredients";
		
	}
		
	}