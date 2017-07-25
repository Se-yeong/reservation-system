package kr.or.reservation.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.domain.Category;
import kr.or.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/categorys")
public class RestCategoryController {

	// 차이점 ?
	@Autowired
	CategoryService catergoryservice;
/*	
	@Autowired
	public RestCategoryController(CategoryService catergoryservice) {
		this.catergoryservice = catergoryservice;
	}
	
	@Autowired
	public void setCategoryService(CategoryService catergoryservice) {
		this.catergoryservice = catergoryservice;
	}
	*/
	
	
	
	@GetMapping
	public List<Category> selectAll(Model model){
    	List<Category> list = catergoryservice.selectforList();
    	return list;
	}
	

}