package com.hmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hmdy.recipe.domain.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	
	Optional<Category> getCategoryByName(String name);
}
