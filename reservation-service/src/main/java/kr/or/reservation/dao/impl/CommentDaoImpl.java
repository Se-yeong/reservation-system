package kr.or.reservation.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.dao.sqls.CommentSqls;
import kr.or.reservation.dao.sqls.ProductSqls;
import kr.or.reservation.domain.Product;
import kr.or.reservation.domain.ReservationUserComment;

public class CommentDaoImpl implements CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserComment.class);
	
	public long insert(ReservationUserComment reservationUserComment) {
		return -1;
	}

	public List<ReservationUserComment> selectList(long productId, int start, int amount) {
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("amount", amount);

		return jdbc.query(CommentSqls.SELECT_LIST, params, rowMapper); 
	}

}
