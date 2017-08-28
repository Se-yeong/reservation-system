package kr.or.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.dao.CommentImageDao;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.domain.ReservationUserComment;
import kr.or.reservation.domain.ReservationUserCommentImage;
import kr.or.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	CommentDao dao;
	CommentImageDao imagedao;

	@Autowired
	public void setCommentDao(CommentDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setImagedao(CommentImageDao imagedao) {
		this.imagedao = imagedao;
	}

	@Override
	public long insert(ReservationUserComment comment) {
		if (comment == null || comment.getComment() == null) {
			throw new IllegalArgumentException();
		}
		return dao.insert(comment);
	}

	@Override
	public List<ReservationUserComment> selectList(long productId, int start, int amount) {
		if (start < 0 || amount <= 0 || productId <= 0) {
			return null;
		}
		return dao.selectList(productId, start, amount);
	}

	@Override
	public List<ReservationUserCommentImage> selectImageList(long id) {
		if (id <= 0) {
			return null;
		}
		return imagedao.selectList(id);
	}

	@Override
	public boolean updatefirstFileName(long commentId, FileDomain file, long imageCount) {
		if (commentId <= 0 || imageCount <= 0) {
			return false;
		}
		return dao.updatefirstFileName(commentId, file.getSaveFileName(), imageCount) == 1;
	}
}