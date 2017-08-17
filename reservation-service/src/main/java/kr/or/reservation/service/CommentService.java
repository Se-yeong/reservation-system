package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.domain.ReservationUserComment;
import kr.or.reservation.domain.ReservationUserCommentImage;

public interface CommentService {
	public List<ReservationUserComment> selectList(long productId, int start, int amount);
	
	public List<ReservationUserCommentImage> selectImageList(long id);
}
