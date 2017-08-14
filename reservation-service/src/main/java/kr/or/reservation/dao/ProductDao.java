package kr.or.reservation.dao;

import java.util.List;

import kr.or.reservation.domain.Product;

public interface ProductDao {
	public Product selectOne(long id);
	
	public List<Product> selectList(long categoryId, int start, int amount);
	
	public List<Product> selectList(int start, int amount);
	
	public Long selectCount();
	
	public Long selectCount(long categoryId);

}
