package com.hmdy.recipe.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTest {
	Recipe recipe;

	@BeforeEach
	void setUp() throws Exception {
		recipe = new Recipe();
	}

	@Test
	void test() {
		String testName="Mohinger";
		recipe.setName(testName);
		assertEquals(testName, recipe.getName());
	}

	@Test
	void testPrepTimeGetter() {
		recipe = new Recipe("Sample", "JustDescription", 20, 20, "JustSource",5,"This is Direction",Difficulty.EASY);
		
		assertNotNull(recipe.getPrepTime());
	}

}
