package kr.or.reservation.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.domain.ReservationUserComment;
import kr.or.reservation.domain.ReservationUserCommentImage;
import kr.or.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	CommentDao dao;

	@Autowired
	public void setCommentDao(CommentDao dao) {
		this.dao = dao;
	}

	public List<ReservationUserComment> selectList(long productId, int start, int amount) {
		if (start < 0 || amount <= 0 || productId <= 0) {
			return null;
		}
		return dao.selectList(productId, start, amount);
	}

	public List<ReservationUserCommentImage> selectImageList(long id) {
		if (id <= 0) {
			return null;
		}

		return dao.selectImageList(id);
	}
}
