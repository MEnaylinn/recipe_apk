package com.hmdy.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hmdy.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	Optional<Recipe> findByName(String name);
}
