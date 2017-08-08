package kr.or.reservation.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dao.CategoryDao;
import kr.or.reservation.dao.sqls.CategorySqls;
import kr.or.reservation.domain.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	@Autowired
	public CategoryDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("category").usingGeneratedKeyColumns("id");
	}

	@Override
	public List<Category> selectList() {
		return jdbc.query(CategorySqls.SELECT_LIST, rowMapper);
	}

	@Override
	public Long insert(Category category) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(category);
		return insertAction.executeAndReturnKey(params).longValue();
	}

	@Override
	public Long delete(Long id) {
		Map<String, ?> map = Collections.singletonMap("id", id);
		return (long) jdbc.update(CategorySqls.DELETE, map);
	}

}
