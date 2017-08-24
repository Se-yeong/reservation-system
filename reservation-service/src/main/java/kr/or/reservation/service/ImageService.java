package kr.or.reservation.service;

import kr.or.reservation.domain.FileDomain;

public interface ImageService {
	public int[] insertFileArray(FileDomain[] files);
	public boolean insertImageArray(long reservationUserCommentId, int[] fileIds);
}
