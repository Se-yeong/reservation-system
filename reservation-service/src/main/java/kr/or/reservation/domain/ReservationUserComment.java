package kr.or.reservation.domain;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

import kr.or.reservation.common.ToStringStyleCustom;

public class ReservationUserComment {
	private long id;
	private long productId;
	private long userId;
	private String score;
	private String comment;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private Integer imageCount;
	private String firstImageSaveFileName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getImage_count() {
		return imageCount;
	}

	public void setImage_count(Integer imageCount) {
		this.imageCount = imageCount;
	}

	public String getFirstImageSaveFileName() {
		return firstImageSaveFileName;
	}

	public void setFirstImageSaveFileName(String firstImageSaveFileName) {
		this.firstImageSaveFileName = firstImageSaveFileName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, new ToStringStyleCustom());
	}

}
