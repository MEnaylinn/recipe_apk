package com.hmdy.recipe.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmdy.recipe.domain.Category;
import com.hmdy.recipe.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories=categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	@GetMapping("/{categoryName}")
	public ResponseEntity<?> getCategoryByName(@PathVariable String categoryName ){
		Optional<Category> categoryOpt=categoryService.getCategoryByName(categoryName);
		
		if(categoryOpt.isEmpty()) {
			return new ResponseEntity<String>("Category with name= "+categoryName+" is not found",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(categoryOpt.get(),HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		Category createdCategory=categoryService.createCategory(category);
		return new ResponseEntity<Category>(createdCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody Category category){
		Optional<Category> categoryOpt=categoryService.getCategoryById(categoryId);
		if(categoryOpt.isEmpty()) {
			return new ResponseEntity<String> ("category with id = "+categoryId+" not present",HttpStatus.NOT_FOUND);
		}
		
		category.setId(categoryId);
		Category updatedCategory=categoryService.createCategory(category);
		return new ResponseEntity<Category>(updatedCategory,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
		Optional<Category> categoryOpt=categoryService.getCategoryById(categoryId);
		if(categoryOpt.isEmpty()) {
			return new ResponseEntity<String> ("category with id = "+categoryId+" not present",HttpStatus.NOT_FOUND);
		}
		
		categoryService.deleteCategoryById(categoryId);
		return new ResponseEntity<String>("category with id = "+ categoryId+" is deleted",HttpStatus.OK);
	}

}
