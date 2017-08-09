package kr.or.reservation.controller.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.domain.Category;
import kr.or.reservation.domain.Product;
import kr.or.reservation.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class RestProductController {

	ProductService service;
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public RestProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping("")
	public List<Product> selectList(@RequestParam(required = false) int start,
			@RequestParam(required = false) int amount) {
		start = convertNulltoZero(start);
		amount = convertNulltoZero(amount);
		return service.selectList(start, amount);
	}

	@GetMapping("/category/{categoryId}")
	public List<Product> selectListByCategoryId(@PathVariable() int categoryId,
			@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer amount) {
		start = convertNulltoZero(start);
		amount = convertNulltoZero(amount);
		return service.selectList(categoryId, start, amount);
	}

	@PostMapping()
	public Long insert(@ModelAttribute Category category) {
		return null;
	}

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable Long id) {
		return null;
	}

	private int convertNulltoZero(Integer num) {
		return num == null ? 0 : num.intValue();
	}

}
