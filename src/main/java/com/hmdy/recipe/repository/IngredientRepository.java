package com.hmdy.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hmdy.recipe.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	Optional<Ingredient> findByDescription(String description);
}
