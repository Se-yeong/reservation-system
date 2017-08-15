package kr.or.reservation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.ProductDao;
import kr.or.reservation.domain.Product;
import kr.or.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	ProductDao dao;
	
	// STATIC을 안 써도 문제없음.
	static Map<Long, Long> cachedCount = new HashMap<>();
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public void setDao(ProductDao dao) {
		this.dao = dao;
	}

	@Override
	public Product selectOne(long id) {
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

	// timestamp를 찍어서, 계산 
	// select 시, timestamp를 계산해서 일정 시간 이상일 경우 - update  
	@Override
	public Long selectCount() {
		Long count = cachedCount.get((long) 0);
		log.info("cached " + count);
		if (count == null) {
			count = dao.selectCount();
			// 1. 동기화 구문을 건다.
			//  - 성능 저하 
			// 2. 별도로 batch를 돌림  
			//  - spring 스케줄링을 통해 사용 가능 (별도의 스레드에서 수행됨) 
			cachedCount.put((long) 0, count);
			log.info("cache miss: " + 0);
			return count;
		}
		log.info("cache hit: " + 0);
		return count;
	}

	@Override
	public Long selectCount(long categoryId) {
		if (categoryId <= 0) {
			return null;
		}
		
		Long count = cachedCount.get(categoryId);
		if (count == null) {
			count = dao.selectCount(categoryId);
			cachedCount.put(categoryId, count);
			log.info("cache miss: " + categoryId);
			return count;
		}
		log.info("cache hit: " + categoryId);
		return count;
	}

}
