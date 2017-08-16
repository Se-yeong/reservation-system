package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.domain.ReservationUserComment;

public interface CommentService {
	public List<ReservationUserComment> selectList(long productId, int start, int amount);
}
