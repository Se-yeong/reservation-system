package kr.or.reservation.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dao.FileDao;
import kr.or.reservation.domain.FileDomain;

@Repository
public class FileDaoImpl implements FileDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	//private RowMapper<FileDomain> rowMapper = BeanPropertyRowMapper.newInstance(FileDomain.class);

	public FileDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("file")
				.usingGeneratedKeyColumns("id");
	}

	public int[] insertArray(FileDomain[] files) {
		int length = files.length;
		int[] keyArray = new int[length];
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(files);
		for(int i = 0; i < length; ++i) {
			keyArray[i] = insertAction.executeAndReturnKey(params[i]).intValue();
		}
		return keyArray;
	}
}
