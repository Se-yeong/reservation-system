package kr.or.reservation.dao.impl;

import java.util.HashMap;
import java.util.List;
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

import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.dao.sqls.CommentSqls;
import kr.or.reservation.domain.ReservationUserComment;

@Repository
public class CommentDaoImpl implements CommentDao {
	Logger log = Logger.getLogger(this.getClass());

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper
			.newInstance(ReservationUserComment.class);
	
	public CommentDaoImpl() {
	}
	
	@Autowired
	public CommentDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment")
				.usingGeneratedKeyColumns("id");
	}
	
	public void setJdbc(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public long insert(ReservationUserComment reservationUserComment) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationUserComment);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	@Override
	public List<ReservationUserComment> selectList(long productId, int start, int amount) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("start", start);
		params.put("amount", amount);
		return jdbc.query(CommentSqls.SELECT_LIST, params, rowMapper);
	}
	
	@Override
	public int updatefirstFileName(long commentId, String firstImageSaveFileName, long imageCount) {
		Map<String , Object> map = new HashMap<>();
		map.put("id", commentId);
		map.put("firstImageSaveFileName", firstImageSaveFileName);
		map.put("imageCount", imageCount);
		return jdbc.update(CommentSqls.UPDATE_FIRST_FILE_NAME,map);
	}
	

}
