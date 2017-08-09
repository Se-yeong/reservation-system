package kr.or.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReservationUserCommentImage {
	private long id;
	private long reservationUserCommentId;
	private long fileId;

	private File file;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(long reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
