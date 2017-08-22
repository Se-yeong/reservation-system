package kr.or.reservation.controller.rest;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class RestReservationController {

	Logger log = Logger.getLogger(this.getClass());
	ReservationService reservationService;

	@Autowired
	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@PostMapping
	public long  insert(Model model, @ModelAttribute ReservationInfo info) {
		log.info("here ::" + info.toString());
		return reservationService.insert(info);
	}
}
