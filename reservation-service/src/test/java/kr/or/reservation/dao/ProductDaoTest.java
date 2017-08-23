package kr.or.reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Fields;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.config.ServletContextConfig;
import kr.or.reservation.dao.impl.ProductDaoImpl;
import kr.or.reservation.dao.sqls.ProductSqls;
import kr.or.reservation.domain.PriceType;
import kr.or.reservation.domain.Product;
import kr.or.reservation.domain.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

@ContextConfiguration(classes = { RootApplicationContextConfig.class, ServletContextConfig.class })
public class ProductDaoTest {

	ProductDaoImpl dao;

	@Mock
	private NamedParameterJdbcTemplate jdbc;

	@Before
	public void setUp() throws SQLException {
		MockitoAnnotations.initMocks(this);
		setUpJdbc();
		dao = new ProductDaoImpl();
		dao.setJdbc(jdbc);
	}

	public void setUpJdbc() {
		when(jdbc.query(anyString(), anyMap(), any(RowMapper.class))).thenReturn(new ArrayList<Product>());
		doReturn(new Product()).when(jdbc).queryForObject(anyString(), anyMap(), any(RowMapper.class));
		doReturn((long)1).when(jdbc).queryForObject(anyString(), anyMap(),long.class);
		
		
	}

	/*
	 * public void setMocks() throws SQLException {
	 * 
	 * when(mockDataSource.getConnection()).thenReturn(mockConnection);
	 * when(mockDataSource.getConnection(anyString(),
	 * anyString())).thenReturn(mockConnection);
	 * doNothing().when(mockConnection).commit();
	 * when(mockConnection.prepareStatement(anyString(),
	 * anyInt())).thenReturn(mockPreparedStatement);
	 * 
	 * doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
	 * doNothing().when(mockPreparedStatement).setObject(anyInt(), anyObject());
	 * when(mockPreparedStatement.execute()).thenReturn(Boolean.TRUE);
	 * when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
	 * 
	 * 
	 * 
	 * when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
	 * when(mockResultSet.getLong("id")).thenReturn((long) 1);
	 * when(mockResultSet.getLong("category_id")).thenReturn((long) 1);
	 * when(mockResultSet.getString("name")).thenReturn("name1");
	 * when(mockResultSet.getString("description")).thenReturn("description1");
	 * 
	 * when(mockResultSet.getString("content")).thenReturn("content1");
	 * 
	 * when(mockResultSet.getString("observation_time")).thenReturn(
	 * "observation_time1");
	 * when(mockResultSet.getString("place_name")).thenReturn("place_name1");
	 * 
	 * when(mockResultSet.getInt("price_type")).thenReturn(1);
	 * when(mockResultSet.getLong("price")).thenReturn((long) 1000); }
	 */

	@Test
	public void shouldSelectOne() {
		Product product;
		
		product = dao.selectOne(1);
	
		verify(jdbc).queryForObject(anyString(), anyMap(), any(RowMapper.class));
		
		System.out.println(dao.selectOne(1));

	}

	@Test
	public void shouldSelectList() {

	}

	@Test
	public void shouldSelectListByCategory() {
	}

	@Test
	public void shouldSelectCount() {
	}

	@Test
	public void shouldSelectCountByCategory() {
	}

	@Test
	public void shouldSelectOnePrice() {
	}

}
