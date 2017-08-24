package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.or.reservation.service.ReservationService;

@Controller
public class ReservationController {
	
	private ReservationService reservationService;
	
	@Autowired
	public void setReservationService (ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/myreservation")
	public String viewMyReservation(Model model) {
		return "myreservation";
	}
	
	@GetMapping("/reservation/{reservationId}/review-write")
	public String getReservation(Model model, @PathVariable long reservationId) {
		model.addAttribute("reservationInfo", reservationService.selectOne(reservationId));
		return "reviewWrite";
	}
}
