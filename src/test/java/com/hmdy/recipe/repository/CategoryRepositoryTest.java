package com.hmdy.recipe.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hmdy.recipe.domain.Category;
import com.jayway.jsonpath.Option;

@SpringBootTest
class CategoryRepositoryTest {
	
	@Autowired
	CategoryRepository categoryRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindAll() {
		List<Category> categories=(List<Category>) categoryRepository.findAll();
		categories.forEach(System.out :: println);
		assertFalse(categories.isEmpty());
	}
	
	@Test
	void testFindById() {
		Optional<Category> cateOptional=categoryRepository.findById(2L);
		assertTrue(cateOptional.isPresent());
	}
	
	@Test
	void testFindByName() {
		Optional<Category> cateOptional=categoryRepository.findByName("Asian");
		assertTrue(cateOptional.isPresent());
	}
	
	@Test
	void testSave() {
		Category category=new Category("Korea", "This is Korean Food");
		Category saveCategory=categoryRepository.save(category);
		System.out.println(saveCategory);
		assertNotNull(saveCategory);
	}
	
	@Test
	void testDelete() {
		categoryRepository.deleteById(3L);
		System.out.println("### Delete category id 3L");
		Optional<Category> cateOptional=categoryRepository.findById(3L);
		assertTrue(cateOptional.isEmpty());
	}

}
