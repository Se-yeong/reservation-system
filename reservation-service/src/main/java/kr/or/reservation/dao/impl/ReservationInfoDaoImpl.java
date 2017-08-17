package kr.or.reservation.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dao.ReservationInfoDao;
import kr.or.reservation.domain.ReservationInfo;

@Repository
public class ReservationInfoDaoImpl implements ReservationInfoDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public ReservationInfoDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info").usingGeneratedKeyColumns("id");
	}

	@Override
	public long insert(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertAction.executeAndReturnKey(params).longValue();
	}

	@Override
	public long update(ReservationInfo reservationInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReservationInfo> selectList(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
