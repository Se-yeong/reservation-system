package kr.or.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.CommentImageDao;
import kr.or.reservation.dao.FileDao;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.domain.ReservationUserCommentImage;
import kr.or.reservation.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	FileDao fileDao;
	CommentImageDao commentImageDao;

	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	

	@Autowired
	public void setCommentImageDao(CommentImageDao commentImageDao) {
		this.commentImageDao = commentImageDao;
	}


	@Override
	public int[] insertFileArray(FileDomain[] files) {
		if (files == null) {
			return null;
		}
		int[] fileId = fileDao.insertArray(files);
		return fileId;
	}

	@Override
	public boolean insertImageArray(long reservationUserCommentId, int[] fileIds) {
		int length = fileIds.length;
		ReservationUserCommentImage[] commentImage = new ReservationUserCommentImage[length];
		for(int i=0; i<length; ++i) {
			ReservationUserCommentImage image = new ReservationUserCommentImage();
			image.setReservationUserCommentId(reservationUserCommentId);
			image.setFileId(fileIds[i]);
			commentImage[i] = image;
		}
		return commentImageDao.insertArray(commentImage);
		
	}
}
