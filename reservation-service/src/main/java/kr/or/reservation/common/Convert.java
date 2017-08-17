package kr.or.reservation.common;

public class Convert {
	public static int convertNulltoZero(Integer num) {
		return num == null ? 0 : num.intValue();
	}
}
