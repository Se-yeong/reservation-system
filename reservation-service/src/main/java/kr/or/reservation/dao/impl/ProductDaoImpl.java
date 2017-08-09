package kr.or.reservation.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dao.ProductDao;
import kr.or.reservation.dao.sqls.ProductSqls;
import kr.or.reservation.domain.DisplayInfo;
import kr.or.reservation.domain.Product;
import kr.or.reservation.domain.ProductDetail;

@Repository
public class ProductDaoImpl implements ProductDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = new ProductMapper();

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public ProductDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Product> selectOne(long id) {
		Map<String, Long> parames = Collections.singletonMap("id", id);
		return jdbc.query(ProductSqls.SELECT_ONE, parames, rowMapper);
	}

	@Override
	public List<Product> selectList(long categoryId, int start, int amount) {
		Map<String, Object> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("amount", amount);
		return jdbc.query(ProductSqls.SELECT_LIST_BY_CATEGORY_ID, params, rowMapper);
	}

	@Override
	public List<Product> selectList(int start, int amount) {
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("amount", amount);

		return jdbc.query(ProductSqls.SELECT_LIST, params, rowMapper);
	}

	private class ProductMapper implements RowMapper<Product> {
		// private RowMapper<Product> rowMapperProduct =
		// BeanPropertyRowMapper.newInstance(Product.class);
		// private RowMapper<ProductDetail> rowMapperProductDetail =
		// BeanPropertyRowMapper.newInstance(ProductDetail.class);
		// private RowMapper<DisplayInfo> rowMapperDisplayInfo =
		// BeanPropertyRowMapper.newInstance(DisplayInfo.class);

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

			Product product = mapProduct(rs);
			product.setProductDetail(mapProductDetail(rs));
			product.setDisplayInfo(mapDisplayInfo(rs));

			/*
			 * Product product = rowMapperProduct.mapRow(rs, rowNum); ProductDetail
			 * productDetail = rowMapperProductDetail.mapRow(rs, rowNum); DisplayInfo
			 * displayInfo = rowMapperDisplayInfo.mapRow(rs, rowNum);
			 * product.setProductDetail(productDetail); product.setDisplayInfo(displayInfo);
			 */

			return product;
		}

		public Product mapProduct(ResultSet rs) throws SQLException {
			Product product = new Product();
			product.setId(rs.getLong("id"));
			product.setCategoryId(rs.getString("category_id"));
			product.setName(rs.getString("name"));
			product.setDescription(rs.getString("description"));
			product.setSalesStart(rs.getTimestamp("sales_start"));
			product.setSalesEnd(rs.getTimestamp("sales_end"));
			product.setSalesFlag(rs.getInt("sales_flag"));
			product.setEvent(rs.getString("event"));
			product.setCreateDate(rs.getTimestamp("create_date"));
			product.setModifyDate(rs.getTimestamp("modify_date"));

			return product;
		}

		public ProductDetail mapProductDetail(ResultSet rs) {
			ProductDetail detail = new ProductDetail();

			try {
				// detail.setId(rs.getLong("id_1"));
				detail.setProductId(rs.getLong("id"));
				detail.setContent(rs.getString("content"));
				// detail.setCreateDate(rs.getTimestamp("create_date_1"));
				// detail.setModifyDate(rs.getTimestamp("modify_date_1"));

			} catch (SQLException e) {
				log.debug("ProductDetail is not found");
				return null;
			}
			return detail;
		}

		public DisplayInfo mapDisplayInfo(ResultSet rs) throws SQLException {

			DisplayInfo display = new DisplayInfo();

			try {
				// display.setId(rs.getLong("id_2"));
				display.setProductId(rs.getLong("id"));
				display.setObservationTime(rs.getString("observation_time"));
				display.setDisplayStart(rs.getTimestamp("display_start"));
				display.setDisplayEnd(rs.getTimestamp("display_end"));
				display.setPlaceName(rs.getString("place_name"));
				display.setPlaceLot(rs.getString("place_lot"));
				display.setPlaceStreet(rs.getString("place_street"));
				display.setTel(rs.getString("tel"));
				display.setHomepage(rs.getString("homepage"));
				display.setEmail(rs.getString("email"));

			} catch (SQLException e) {
				log.debug("DisplayInfo is not found");
				return null;
			}

			return display;
		}
	}

}