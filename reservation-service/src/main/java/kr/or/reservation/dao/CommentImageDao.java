package kr.or.reservation.dao;

import java.util.List;

import kr.or.reservation.domain.ReservationUserCommentImage;

public interface CommentImageDao {
	public long insert(ReservationUserCommentImage reservationUserCommentImage);

	public List<ReservationUserCommentImage> selectList(long commentId);
}
