package won.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value= {"/myreservation"})
public class MyReservationController {
	
	@GetMapping
	public String main() {
		return "myreservation";
	}
}
