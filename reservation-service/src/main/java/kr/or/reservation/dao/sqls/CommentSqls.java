package kr.or.reservation.dao.sqls;

public class CommentSqls {
	public static final String SELECT_LIST = " SELECT * FROM reservation_user_comment t1" 
			+ " WHERE t1.product_id = 1"
			+ " LIMIT 0, 10";
}
