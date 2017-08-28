package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.dao.CommentImageDao;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.domain.ReservationUserComment;
import kr.or.reservation.domain.ReservationUserCommentImage;

public interface CommentService {
	
	public void setCommentDao(CommentDao dao);
	
	public void setImagedao(CommentImageDao imagedao);
	
	public List<ReservationUserComment> selectList(long productId, int start, int amount);
	
	public List<ReservationUserCommentImage> selectImageList(long id);
	
	public long insert(ReservationUserComment comment);
	
	public boolean updatefirstFileName(long commentId, FileDomain file, long ImageCount);
	
}
