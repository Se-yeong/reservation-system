package kr.or.reservation.domain;

import java.sql.Timestamp;

public class Users {
	private long id;
	private String username;
	private String email;
	private String tel;
	private String nickname;
	private String snsId;
	private String snsType;
	private String snsProfile;
	private int adminFlag;
	private Timestamp createDate;
	private Timestamp modifyDate;
}
