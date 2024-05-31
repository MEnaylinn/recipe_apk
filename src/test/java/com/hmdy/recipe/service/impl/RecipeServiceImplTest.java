package com.hmdy.recipe.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hmdy.recipe.domain.Recipe;
import com.hmdy.recipe.repository.CategoryRepository;
import com.hmdy.recipe.repository.RecipeRepository;
import com.hmdy.recipe.service.RecipeService;
import com.jayway.jsonpath.Option;

class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	@Mock
	CategoryRepository categoryRepository;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		recipeService=new RecipeServiceImpl(recipeRepository, categoryRepository);
	}

	@Test
	void testGetAllRecipe() {
		List<Recipe> fakeRecipes=new ArrayList<>();
		Recipe recipe1=new Recipe();
		Recipe recipe2=new Recipe();
		
		fakeRecipes.add(recipe1);
		fakeRecipes.add(recipe2);
		
		when(recipeRepository.findAll()).thenReturn(fakeRecipes);
		
		List<Recipe> recipes=recipeService.getAllRecipe();
		
//		for(Recipe r:recipes) {
//			System.out.println(r);
//		}
		
		//check size
		assertEquals(fakeRecipes.size(), recipes.size());
		
		//test method call time
		verify(recipeRepository,times(1)).findAll();
	}
	
	@Test
	void testGetRecipeById() {
		Recipe recipe1 = new Recipe();
		recipe1.setId(3L);
		
		Optional<Recipe> fakeRecipeOpt=Optional.of(recipe1);
		
		when(recipeRepository.findById(anyLong())).thenReturn(fakeRecipeOpt);
		
		Optional<Recipe> recipeOpt=recipeService.getRecipeById(3L);
		
		assertTrue(recipeOpt.isPresent());
		
		
		
	}

}
