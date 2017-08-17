package kr.or.reservation.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.dao.sqls.CommentSqls;
import kr.or.reservation.domain.File;
import kr.or.reservation.domain.ProductImage;
import kr.or.reservation.domain.ReservationUserComment;
import kr.or.reservation.domain.ReservationUserCommentImage;

@Repository
public class CommentDaoImpl implements CommentDao {
	Logger log = Logger.getLogger(this.getClass());

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper
			.newInstance(ReservationUserComment.class);
	private RowMapper<ReservationUserCommentImage> imageMapper = new CommentImageMapper();

	public CommentDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public long insert(ReservationUserComment reservationUserComment) {
		return -1;
	}

	public List<ReservationUserComment> selectList(long productId, int start, int amount) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("start", start);
		params.put("amount", amount);
		return jdbc.query(CommentSqls.SELECT_LIST, params, rowMapper);
	}

	public List<ReservationUserCommentImage> selectImageList(long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return jdbc.query(CommentSqls.SELECT_IMAGE_LIST, params, imageMapper);
	}

	
	
	private class CommentImageMapper implements RowMapper<ReservationUserCommentImage> {

		@Override
		public ReservationUserCommentImage mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReservationUserCommentImage image = mapCommentImage(rs);
			image.setFile(mapFile(rs));
			return image;
		}

		public ReservationUserCommentImage mapCommentImage(ResultSet rs) throws SQLException {

			ReservationUserCommentImage image;

			try {
				image = new ReservationUserCommentImage();
				image.setFileId(rs.getInt("file_id"));
				image.setId(rs.getInt("id"));
				image.setReservationUserCommentId(rs.getInt("reservation_user_comment_id"));
			} catch (SQLException e) {
				log.debug("image is not found");
				return null;
			}
			return image;
		}

		public File mapFile(ResultSet rs) throws SQLException {

			File file = null;

			try {
				file = new File();
				file.setContentType(rs.getString("content_type"));
				// file.setCreateDate(rs.getString(createDate));
				file.setDeleteFlag(rs.getInt("delete_flag"));
				file.setFileLength(rs.getInt("file_length"));
				file.setFileName(rs.getString("file_name"));
				file.setId(rs.getInt("id"));
				file.setSaveFileName(rs.getString("save_file_name"));
				file.setUserId(rs.getInt("user_id"));
			} catch (SQLException e) {
				log.debug("file is not found");
				return null;
			}

			return file;
		}

	}

}
