package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.reservation.service.CategoryService;
import kr.or.reservation.service.ProductService;

@Controller
@RequestMapping("/")
public class MainController {

	ProductService productService;
	CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public String viewMain(Model model) {
		model.addAttribute("category", categoryService.selectList());
		return "mainpage";
	}

}
