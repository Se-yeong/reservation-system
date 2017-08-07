package kr.or.reservation.dao;

import java.util.List;

import kr.or.reservation.domain.Category;

public interface CategoryDao {
	public List<Category> selectList();

	public Long insert(Category category);

	public Long delete(Long id);
}
