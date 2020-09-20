package com.tmn.testing.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tmn.testing.entity.Category;
import com.tmn.testing.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/list")
	public String categories(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		return "category/categories";
	}

	@GetMapping("/new")
	public String newCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category/new";
	}

	@PostMapping("/new")
	public String createCategory(@Valid Category category,RedirectAttributes redirectAttributes,
			Errors error, Model model) {

		if (error.hasErrors()) {
			return "category/new";
		}

		if (categoryService.existsByName(category)) {
			//redirectAttributes.addFlashAttribute("message", "Already Exists");
		    //redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			model.addAttribute("message", "Already Exists");
			model.addAttribute("alertClass", "alert-danger");
			return "category/new";
		} else {
			redirectAttributes.addFlashAttribute("message", "Success");
		    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			this.categoryService.createCategory(category);
			return "redirect:/category/list";
		}
		
	}

	@GetMapping("/edit/{id}")
	public String editCategory(@PathVariable long id, Model model) {
		Category category = this.categoryService.findById(id);
		model.addAttribute("category", category);
		return "category/edit";
	}

	@PostMapping("/edit")
	public String updateCategory(@Valid Category category, RedirectAttributes redirectAttributes,
			Errors error, Model model) {
		if (error.hasErrors()) {
			return "category/edit";
		}

		if (categoryService.existsByName(category)) {
			model.addAttribute("message", "Already Exists");
			model.addAttribute("alertClass", "alert-danger");
			//model.addAttribute("existedCategory", category);
			return "category/edit";
		} else {
			redirectAttributes.addFlashAttribute("message", "Success");
		    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			this.categoryService.updateCategory(category);
			return "redirect:/category/list";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable long id,Model model) {
		try {
			this.categoryService.deleteCategoryById(id);
			model.addAttribute("message", "Delete Successfully!");
			return "redirect:/category/list";
		} catch (Exception e) {
			model.addAttribute("message", "This category has items");
			return "category/list";
		}
	}
}
