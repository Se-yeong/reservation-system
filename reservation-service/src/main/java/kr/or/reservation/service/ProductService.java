package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.domain.Product;

public interface ProductService {
	public List<Product> selectOne(long id);

	public List<Product> selectList(long categoryId, int start, int amount);

	public List<Product> selectList(int start, int amount);
}