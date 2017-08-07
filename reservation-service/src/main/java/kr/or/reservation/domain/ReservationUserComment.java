package kr.or.reservation.domain;

import java.sql.Timestamp;

public class ReservationUserComment {
	private long id;
	private long productId;
	private long userId;
	private String score;
	private String comment;
	private Timestamp createDate;
	private Timestamp modifyDate;
}
