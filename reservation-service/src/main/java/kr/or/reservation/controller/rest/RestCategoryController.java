package kr.or.reservation.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/api/category")
public class RestCategoryController {

	CategoryService service;
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public RestCategoryController(CategoryService categoryService) {
		this.service = categoryService;
	}

	@GetMapping()
	public List<Category> selectList() {
		return service.selectList();
	}

	@PostMapping()
	public Long insert( @Valid @ModelAttribute Category category ,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			log.info("valid");
			return null;
		}
		return service.insert(category);
	}

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable Long id) {
		return service.delete(id);
	}
	
}
