package kr.or.reservation.domain;

import java.sql.Timestamp;

public class File {
	private long id;
	private long userId;
	private String fileName;
	private String saveFileName;
	private int fileLength;
	private String contentType;
	private int deleteFlag;
	private Timestamp createDate;
	private Timestamp modifyDate;
}
