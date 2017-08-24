package kr.or.reservation.dao.sqls;

public class CommentSqls {
	public static final String SELECT_LIST = " SELECT * FROM reservation_user_comment"
			+ " WHERE product_id = :productId" + " LIMIT :start, :amount";

	public static final String SELECT_IMAGE_LIST = "SELECT * FROM reservation_user_comment_image t1 "
			+ " INNER JOIN file t2 ON t1.file_id = t2.id " + " WHERE t1.reservation_user_comment_id = :commentId";

	public final static String UPDATE_FIRST_FILE_NAME = "UPDATE reservation_user_comment " 
			+ "SET first_image_save_file_name = :firstImageSaveFileName," 
			+ "image_count = :imageCount " 
			+ "WHERE id = :id ";
}
