package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.reservation.service.CategoryService;
import kr.or.reservation.service.ProductService;

@Controller
@RequestMapping("/product")
public class DetailController {

	ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public String viewMain(Model model, @PathVariable long id) {
		model.addAttribute("product", productService.selectOne(id));
		return "detail";
	}

}
