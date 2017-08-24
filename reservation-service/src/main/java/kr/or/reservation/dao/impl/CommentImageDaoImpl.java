package kr.or.reservation.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dao.CommentImageDao;
import kr.or.reservation.dao.sqls.CommentSqls;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.domain.ReservationUserCommentImage;

@Repository
public class CommentImageDaoImpl implements CommentImageDao {
	Logger log = Logger.getLogger(this.getClass());

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;

	private RowMapper<ReservationUserCommentImage> imageMapper = new CommentImageMapper();

	@Autowired
	public CommentImageDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment_image")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public boolean insertArray(ReservationUserCommentImage[] commentImages) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(commentImages);
		return insertAction.executeBatch(batch) != null;
	}

	@Override
	public long insert(ReservationUserCommentImage image) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(image);

		return insertAction.executeAndReturnKey(params).longValue();
	}

	@Override
	public List<ReservationUserCommentImage> selectList(long commentId) {
		Map<String, Object> params = new HashMap<>();
		params.put("commentId", commentId);
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

		public FileDomain mapFile(ResultSet rs) throws SQLException {

			FileDomain file = null;

			try {
				file = new FileDomain();
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
