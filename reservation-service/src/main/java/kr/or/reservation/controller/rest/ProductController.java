package kr.or.reservation.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.domain.Category;
import kr.or.reservation.service.CategoryService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	CategoryService categoryService;

	@Autowired
	public ProductController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping()
	public List<Category> selectList() {
		return null;
	}

	@PostMapping()
	public Long insert(@ModelAttribute Category category) {
		return null;
	}

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable Long id) {
		return null;
	}
}
