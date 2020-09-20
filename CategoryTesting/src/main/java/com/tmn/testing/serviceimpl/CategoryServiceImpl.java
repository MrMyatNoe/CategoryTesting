package com.tmn.testing.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmn.testing.dao.CategoryJpaRepository;
import com.tmn.testing.entity.Category;
import com.tmn.testing.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryJpaRepository catRepo;

	@Override
	public List<Category> getAllCategories() {
		return this.catRepo.findAll();
	}

	@Override
	public Category createCategory(Category cat) {
		cat.setCreated_date(new Date());
		cat.setUpdated_date(new Date());
		return this.catRepo.save(cat);
	}

	@Override
	public void deleteCategoryById(long id) {
		this.catRepo.deleteById(id);
	}

	@Override
	public Boolean existsByName(Category cat) {
		Category category = this.catRepo.categoryExists(cat.getName());
		return category != null && category.getId() > 0 && category.getId() != cat.getId();
	}

	@Override
	public Category findById(long id) {
		return this.catRepo.getOne(id);
	}

	@Override
	public Category updateCategory(Category cat) {
		cat.setUpdated_date(new Date());
		return this.catRepo.save(cat);
	}

}
