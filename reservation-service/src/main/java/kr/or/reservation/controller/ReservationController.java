package kr.or.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

	@GetMapping("/myreservation")
	public String viewMyReservation(Model model) {
		return "myreservation";
	}
	
	
}
