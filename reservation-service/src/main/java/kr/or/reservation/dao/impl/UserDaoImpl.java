package kr.or.reservation.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dao.UserDao;
import kr.or.reservation.dao.sqls.UserSqls;
import kr.or.reservation.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	Logger log = Logger.getLogger(this.getClass());

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);

	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("users").usingGeneratedKeyColumns("id");
	}
	@Override
	public long insert(User user) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	@Override
	public long update(User user) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		return (long) jdbc.update(UserSqls.UPDATE, params);
	}
	@Override
	public User selectOneBySnsId(String snsId) {
		Map<String, String> params = Collections.singletonMap("snsId", snsId);
		return jdbc.queryForObject(UserSqls.SELECT_ONE_BY_SNS_ID, params, rowMapper);
	}
	@Override
	public Integer existByNaverId(String naverId) {
		Map<String, Object> params = new HashMap<>();
		params.put("snsId", naverId);
		return jdbc.queryForObject(UserSqls.EXISTS_BY_NAVER_ID, params, Integer.class);
	}
}
