package kr.or.reservation.dao;

import java.util.List;

import kr.or.reservation.domain.ReservationUserComment;

public interface CommentDao {
	public long insert(ReservationUserComment reservationUserComment);

	public List<ReservationUserComment> selectList(long productId, int start, int amount);
	
}
