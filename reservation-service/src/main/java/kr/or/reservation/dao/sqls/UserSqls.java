package kr.or.reservation.dao.sqls;

public class UserSqls {
	static String SELECT_ONE_BY_SNS_ID = "SELECT * FROM users WHERE sns_id = :snsId";
	static String UPDATE = "UPDATE users"
			+ " SET modify_date = NOW() WHERE id = :id";
}
