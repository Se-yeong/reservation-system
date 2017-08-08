package kr.or.reservation.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import kr.or.reservation.dao.ProductDao;
import kr.or.reservation.dao.sqls.CategorySqls;
import kr.or.reservation.domain.Category;
import kr.or.reservation.domain.Product;

public class ProductDaoImpl implements ProductDao{

	@Override
	public List<Product> selectOne(long id) {
		return null;
	}

	@Override
	public List<Product> selectList(long categoryId, int start, int amount) {
		return null;
	}

	@Override
	public List<Product> selectList(int start, int amount) {
		return null;
	}



}
