package kr.or.reservation.domain;

import java.sql.Timestamp;

public class ReservationInfo {
	private long id;
	private long productId;
	private long userId;
	private int generalTicketCount;
	private int youthTicketCount;
	private int childTicketCount;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private Timestamp reservationDate;
	private int reservationType;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private int  totalPrice;
}
