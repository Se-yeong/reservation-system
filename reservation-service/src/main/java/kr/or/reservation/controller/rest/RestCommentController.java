package kr.or.reservation.controller.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.common.Convert;
import kr.or.reservation.domain.Category;
import kr.or.reservation.domain.ReservationUserComment;
import kr.or.reservation.domain.ReservationUserCommentImage;
import kr.or.reservation.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class RestCommentController {
	CommentService service;
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public RestCommentController(CommentService service) {
		this.service = service;
	}

	@GetMapping
	public List<ReservationUserComment> selectList(
			@RequestParam long productId,
			@RequestParam(required = false) int start,
			@RequestParam(required = false) int amount) {
		start = Convert.convertNulltoZero(start);
		amount = Convert.convertNulltoZero(amount);
		return service.selectList(productId, start, amount);
	}
	
	@PostMapping()
	public Long insert( @ModelAttribute ReservationUserComment comment ) {
		return service.insert(comment);
	}
	
	
	@GetMapping("{id}/image")
	public List<ReservationUserCommentImage> selectImageList(@PathVariable long id) {
		return service.selectImageList(id);
	}
}
