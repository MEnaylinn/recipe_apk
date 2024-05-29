package com.hmdy.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hmdy.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
Optional<Category> findByName(String name);
}
