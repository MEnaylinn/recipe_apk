package com.hmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hmdy.recipe.domain.Category;
import com.hmdy.recipe.repository.CategoryRepository;
import com.hmdy.recipe.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;
	
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Optional<Category> getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByName(name);
	}

	@Override
	public Category createCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategoryById(Long id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
		
	}

	@Override
	public Optional<Category> getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

}
