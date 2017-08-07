package kr.or.reservation.domain;

import java.sql.Timestamp;

public class productPrice {

	private long id;
	private long productId;
	private int priceType;
	private long price;
	private String discountRate;
	private Timestamp createDate;
	private Timestamp modifyDate;
}
