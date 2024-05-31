package com.hmdy.recipe.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.hmdy.recipe.domain.Recipe;
import com.hmdy.recipe.repository.RecipeRepository;
import com.hmdy.recipe.service.RecipeService;

class HomeControllerTest {
	HomeController homeController;
	
	//mock testing
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		homeController=new HomeController(recipeService);
		 
	}
	
	@Test
	void testMvcHomeController() throws Exception {
		MockMvc mockMvc=MockMvcBuilders.standaloneSetup(homeController).build();
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
		
	}

	@Test
	void testShowAllRecipes() {
		List<Recipe> fakeRecipes=new ArrayList<>();
		Recipe recipe1=new Recipe();
		Recipe recipe2=new Recipe();
		fakeRecipes.add(recipe1);
		fakeRecipes.add(recipe2);
		
		//return fake recipes
		when(recipeService.getAllRecipe()).thenReturn(fakeRecipes);
		
		ArgumentCaptor<List<Recipe>> captor=ArgumentCaptor.forClass(List.class);
		
		List<Recipe> recipeOpt=recipeService.getAllRecipe();
		assertEquals(2, recipeOpt.size());

		
		String templateName=homeController.showAllRecipe(model);
		
		verify(model).addAttribute(eq("recipes"),captor.capture());
		

		
		assertEquals("index",templateName);
		assertEquals(2, captor.getValue().size());
		
		verify(recipeService,times(2)).getAllRecipe();
		
		
	}

}
