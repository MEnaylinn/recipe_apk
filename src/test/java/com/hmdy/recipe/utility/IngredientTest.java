package com.hmdy.recipe.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hmdy.recipe.domain.Ingredient;

class IngredientTest {
	List<Ingredient> ingredients;

	@BeforeEach
	void setUp() throws Exception {
		ingredients = Arrays.asList(
				new Ingredient(1L, "oil", new BigDecimal(2.5)),
				new Ingredient(3L, "salt", new BigDecimal(3.0)),
				new Ingredient(5L, "spicy pepper", new BigDecimal(1.0)),
				new Ingredient(6L, "vinegar", new BigDecimal(4.0)),
				new Ingredient(2L, "rice", new BigDecimal(1.0)),
				new Ingredient(8L, "msg", new BigDecimal(1.0))
				
				);
				
	}

	@Test
	void testNaturalOrderSorting() {
		List<Ingredient> sortedIngredients = ingredients.stream()
				.sorted((i1,i2)->i1.getId().compareTo(i2.getId()))
				.toList();
		
		System.out.println(sortedIngredients);
		
		assertNotNull(sortedIngredients);
	}

}
