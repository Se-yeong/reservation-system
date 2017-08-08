package kr.or.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.CategoryDao;
import kr.or.reservation.domain.Category;
import kr.or.reservation.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	CategoryDao categoryDao;

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> selectList() {
		// TODO Auto-generated method stub
		return categoryDao.selectList();
	}

	@Override
	public Long insert(Category category) {
		// TODO Auto-generated method stub
		if (category == null || category.getName() == null || "".equals(category.getName())) {
			return null;
		}
		return categoryDao.insert(category);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		if (id <= 0) {
			return null;
		}
		return categoryDao.delete(id);
	}

}
