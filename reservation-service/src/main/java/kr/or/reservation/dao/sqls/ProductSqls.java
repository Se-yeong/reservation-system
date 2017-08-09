package kr.or.reservation.dao.sqls;

public class ProductSqls {
	public static final String SELECT_LIST = "SELECT * FROM product t1 "
			+ "INNER JOIN product_image t2 ON t1.id = t2.product_id " 
			+ "INNER JOIN FILE t3 ON t2.file_id = t3.id "
			+ "LEFT OUTER JOIN display_info t4 ON t1.id = t4.product_id "
			+ "WHERE sales_flag = 0 AND type = 1 LIMIT :start , :amount;";

	public static final String SELECT_LIST_BY_CATEGORY_ID = "SELECT * FROM product t1 "
			+ "INNER JOIN product_image t2 ON t1.id = t2.product_id " 
			+ "INNER JOIN FILE t3 ON t2.file_id = t3.id "
			+ "LEFT OUTER JOIN display_info t4 ON t1.id = t4.product_id "
			+ "WHERE category_id = :categoryId AND sales_flag = 0 AND type = 1 LIMIT :start , :amount;";

	public static final String SELECT_ONE = "SELECT * FROM product "
			+ "LEFT OUTER JOIN product_detail ON product.id = product_detail.product_id "
			+ "LEFT OUTER JOIN display_info ON product.id = display_info.product_id " 
			+ "WHERE product.id= :id; ";
	
	 /*"SELECT * " 
		+ "FROM product t1 "
		+ "LEFT OUTER JOIN product_detail t4 ON t1.id = t4.product_id "
		+ "LEFT OUTER JOIN display_info t5 ON t1.id = t5.product_id " 
		+ "WHERE t1.id= :id; ";*/
}