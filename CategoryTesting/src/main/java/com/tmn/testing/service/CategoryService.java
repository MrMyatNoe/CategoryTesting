package com.tmn.testing.service;

import java.util.List;

import com.tmn.testing.entity.Category;


public interface CategoryService {

	List<Category> getAllCategories();

	Category createCategory(Category cat);
	
	Category updateCategory(Category cat);

	void deleteCategoryById(long id);

	Boolean existsByName(Category cat);
	
	Category findById(long id);
}
