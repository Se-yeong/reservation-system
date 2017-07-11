package kr.or.connect.reservation.service.impl;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDao categoryDao;

    @Transactional(readOnly = true)
	public Category get(Long id) {
		// TODO Auto-generated method stub
		return categoryDao.selectById(id);
	}

    
    @Transactional(readOnly = false)
	public Category addMember(Category category) {
		// TODO Auto-generated method stub
		Long insert = categoryDao.insert(category);
		category.setId(insert);
        return category;

	}
    
    
    public Collection<Category> getAll()
    {
    		return categoryDao.selectAll();
    }
    
    @Transactional(readOnly = false)
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		int affected = categoryDao.deleteById(id);
		return affected == 1;
	}

    
	public boolean update(Category category) {
		// TODO Auto-generated method stub
		int affected = categoryDao.update(category);
		return affected == 1;
	}
}
