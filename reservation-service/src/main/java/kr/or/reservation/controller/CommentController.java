package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.or.reservation.domain.Product;
import kr.or.reservation.service.CommentService;
import kr.or.reservation.service.ProductService;
import kr.or.reservation.service.ReservationService;

@Controller
public class CommentController {
	private CommentService commentService;
	private ProductService productService;
	private ReservationService reservationService;
	
	@Autowired
	public void setCommentService (CommentService commentService) {
		this.commentService = commentService;
	}
	
	@Autowired
	public void setProductService (ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	public void setReservationService (ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@GetMapping("/product/{productId}/review")
	public String getProduct(Model model, @PathVariable int productId) {
		Product product = productService.selectOne(productId);
		
		model.addAttribute("product", product);
		
		return "review";
	}
	
	@GetMapping("/reservation/{reservationId}/review-write")
	public String getReservation(Model model, @PathVariable long reservationId) {
		model.addAttribute("reservationInfo", reservationService.selectOne(reservationId));
		return "reviewWrite";
	}
}
