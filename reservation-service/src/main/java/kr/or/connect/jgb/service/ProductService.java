package kr.or.connect.jgb.service;

import java.util.List;


import kr.or.connect.jgb.domain.Product;
import kr.or.connect.jgb.domain.vo.ProductMainVO;

public interface ProductService {
	public Product get(int id);
	public List<ProductMainVO> getAllByCategory(int id,int page);
	public List<ProductMainVO> getAll(int page);
    public Product addProduct(Product product);
    public int delete(int id);
    public int update(Product product);
}
