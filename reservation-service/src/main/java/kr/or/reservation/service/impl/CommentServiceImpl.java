package kr.or.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.domain.ReservationUserComment;
import kr.or.reservation.service.CommentService;

public class CommentServiceImpl implements CommentService{
	CommentDao dao;

	@Autowired
	public void setCommentDao(CommentDao dao) {
		this.dao = dao;
	}
	
	public List<ReservationUserComment> selectList(long productId, int start, int amount){
		if (start < 0 || amount <= 0 || productId <= 0) {
			return null;
		}
		return dao.selectList(productId, start, amount);
	}
}
