package kr.or.reservation.service;

import java.util.List;
import java.util.Map;

import kr.or.reservation.dao.ProductDao;
import kr.or.reservation.domain.Product;

public interface ProductService {
	
	public void setDao(ProductDao dao);
	
	public void setCachedCount(Map<Long, Long> cachedCount);
	
	public Product selectOne(long id);

	public List<Product> selectList(long categoryId, int start, int amount);

	public List<Product> selectList(int start, int amount);
	
	public Long selectCount();
	
	public Long selectCount(long categoryId);
	
	public Product selectOnePrice(long id);
}
