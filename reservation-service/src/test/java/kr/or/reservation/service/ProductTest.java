package kr.or.reservation.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ProductTest {

	@Autowired
	ProductService service;
	Logger log = Logger.getLogger(this.getClass());

	@Test
	public void shouldSelectList() {
		int start = 0, amount = 10;
		List<Product> products = service.selectList(start, amount);
		log.info("shouldSelectList: " + products.toString());
		Assert.assertNotNull(products);
	}

	@Test
	public void shouldSelectListByCategoryId() {
		int start = 0, amount = 10;
		List<Product> products = service.selectList(1, start, amount);
		log.info("shouldSelectListByCategoryId: " + products.toString());
		Assert.assertNotNull(products);
	}

	@Test
	public void shouldSelectOne() {
		Product products = service.selectOne(2);
		log.info("shouldSelectOne: " + products.toString());
		
		Assert.assertNotNull(products);
	}

	@Test
	public void shouldSelectCount() {
		Long count = service.selectCount();
		log.info("shouldSelectCount: " + count.toString());
		Assert.assertNotNull(count);
	}

	@Test
	public void shouldSelectCountByCategoryId() {
		Long count = service.selectCount(1);
		log.info("shouldSelectCountByCategoryId: " + count.toString());
		Assert.assertNotNull(count);
	}

}
