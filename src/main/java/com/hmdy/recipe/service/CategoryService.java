package com.hmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hmdy.recipe.domain.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	
	Optional<Category> getCategoryById(Long id);
	Optional<Category> getCategoryByName(String name);
	
	Category createCategory(Category category);
	
	void deleteCategoryById(Long id);
	
}
