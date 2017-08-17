package kr.or.reservation.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

	Logger log = Logger.getLogger(this.getClass());
	
	@PostMapping("/rservation")
	public String insert(Model model) {
		log.info("here ::");
		return "redirect:/";
	}
}
