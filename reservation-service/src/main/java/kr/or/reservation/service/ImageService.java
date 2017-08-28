package kr.or.reservation.service;

import kr.or.reservation.dao.CommentImageDao;
import kr.or.reservation.dao.FileDao;
import kr.or.reservation.domain.FileDomain;

public interface ImageService {
	public void setFileDao(FileDao fileDao);
	public void setCommentImageDao(CommentImageDao commentImageDao);
	public int[] insertFileArray(FileDomain[] files);
	public boolean insertImageArray(long reservationUserCommentId, int[] fileIds);
}
