package kr.or.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.ProductDao;
import kr.or.reservation.domain.Product;
import kr.or.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	ProductDao dao;

	@Autowired
	public void setDao(ProductDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Product> selectOne(long id) {
		if (id <= 0) {
			return null;
		}
		return dao.selectOne(id);
	}

	@Override
	public List<Product> selectList(long categoryId, int start, int amount) {
		if (start < 0 || amount <= 0 || categoryId <= 0) {
			return null;
		}
		return dao.selectList(categoryId, start, amount);
	}

	@Override
	public List<Product> selectList(int start, int amount) {
		if (start < 0 || amount <= 0) {
			return null;
		}
		return dao.selectList(start, amount);
	}

}
