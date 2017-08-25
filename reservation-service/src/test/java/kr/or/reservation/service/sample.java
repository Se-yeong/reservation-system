package kr.or.reservation.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class sample {
	public static void main(String[] args) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/reservationSystem/CommentDBTest;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		
		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);
		String sql = "CREATE TABLE t1 (id int); " + 
				"INSERT INTO t1 VALUES (1); " + 
				"INSERT INTO t1 VALUES (2); " +
				"SELECT COUNT(*) FROM t1;";
		
		Map<String, Object> params = Collections.emptyMap();
		Integer count = jdbc.queryForObject(sql, params, Integer.class);
		System.out.println(count);
	}
}
