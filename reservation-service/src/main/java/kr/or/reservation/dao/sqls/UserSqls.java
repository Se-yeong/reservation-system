package kr.or.reservation.dao.sqls;

public class UserSqls {
	public static String SELECT_ONE_BY_SNS_ID = "SELECT * FROM users WHERE sns_id = :snsId";
	public static String UPDATE = "UPDATE users"
			+ " SET modify_date = NOW() WHERE id = :id";
	public static String EXISTS_BY_NAVER_ID = "select exists (select * from users where sns_id = :snsId) as success";
}
