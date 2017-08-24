package kr.or.reservation.service;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.assertj.core.api.AssertFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.dao.ProductDao;
import kr.or.reservation.domain.Product;
import kr.or.reservation.service.impl.ProductServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ProductServcieTest {

	/*
	 * @Autowired ProductService service; Logger log =
	 * Logger.getLogger(this.getClass());
	 * 
	 * @Test public void shouldSelectList() { int start = 0, amount = 10;
	 * List<Product> products = service.selectList(start, amount);
	 * log.info("shouldSelectList: " + products.toString());
	 * Assert.assertNotNull(products); }
	 * 
	 * @Test public void shouldSelectListByCategoryId() { int start = 0, amount =
	 * 10; List<Product> products = service.selectList(1, start, amount);
	 * log.info("shouldSelectListByCategoryId: " + products.toString());
	 * Assert.assertNotNull(products); }
	 * 
	 * @Test public void shouldSelectOne() { Product products =
	 * service.selectOne(2); log.info("shouldSelectOne: " + products.toString());
	 * Assert.assertNotNull(products); }
	 * 
	 * @Test public void shouldSelectCount() { Long count = service.selectCount();
	 * log.info("shouldSelectCount: " + count.toString());
	 * Assert.assertNotNull(count); }
	 * 
	 * @Test public void shouldSelectCountByCategoryId() { Long count =
	 * service.selectCount(1); log.info("shouldSelectCountByCategoryId: " +
	 * count.toString()); Assert.assertNotNull(count); }
	 * 
	 * @Test public void shouldSelectOnePrice() { Product products =
	 * service.selectOnePrice(1); log.info("shouldSelectOnePrice: " +
	 * products.toString()); Assert.assertNotNull(products); }
	 */

	@Mock
	ProductDao dao;

	@Mock
	Map<Long, Long> cachedCount;

	ProductService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new ProductServiceImpl();
		service.setDao(dao);
		service.setCachedCount(cachedCount);
	}

	@Test
	public void shouldSelectOne() {
		long id = 1;
		Product product = null;
		when(dao.selectOne(id)).thenReturn(new Product().setId(id));

		product = service.selectOne(id);

		verify(dao, times(1)).selectOne(id);
		Assert.assertThat(product.getId(), is(id));
	}

	@Test
	public void shouldSelectListByCategory() {
		long category = 1;
		int start = 1, amount = 3;
		List list = null;
		when(dao.selectList(category, start, amount)).thenReturn(new ArrayList<>());

		list = service.selectList(category, start, amount);

		verify(dao, times(1)).selectList(category, start, amount);
		Assert.assertNotNull(list);
	}

	@Test
	public void shouldSelectList() {
		int start = 1, amount = 3;
		List list = null;
		when(dao.selectList(start, amount)).thenReturn(new ArrayList<>());

		list = service.selectList(start, amount);

		verify(dao, times(1)).selectList(start, amount);
		Assert.assertNotNull(list);
	}

	// timestamp를 찍어서, 계산
	// select 시, timestamp를 계산해서 일정 시간 이상일 경우 - update
	@Test
	public void shouldSelectCachedCount() {
		long key = 0;
		long count = 0;
		when(cachedCount.get(key)).thenReturn((long) 2);

		count = service.selectCount();

		verify(dao, times(0)).selectCount();
		verify(cachedCount, times(1)).get(key);
		Assert.assertThat(count, is((long) 2));
	}

	@Test
	public void shouldSelectNotCachedCount() {
		long key = 0;
		long count;
		when(cachedCount.get(key)).thenReturn(null);
		when(dao.selectCount()).thenReturn((long) 1);

		count = service.selectCount();

		verify(dao, times(1)).selectCount();
		verify(cachedCount, times(1)).get(key);
		verify(cachedCount, times(1)).put(key, count);
		Assert.assertThat(count, is((long) 1));
	}

	@Test
	public void selectCachedCountByCategory() {
		long count = 0;
		long category = 1;
		when(cachedCount.get(category)).thenReturn((long) 2);

		count = service.selectCount(category);

		verify(dao, times(0)).selectCount(category);
		verify(cachedCount, times(1)).get(category);
		Assert.assertThat(count, is((long) 2));
	}

	@Test
	public void selectNotCachedCountByCategory() {
		long count = 0;
		long category = 1;
		when(cachedCount.get(category)).thenReturn(null);
		when(dao.selectCount(category)).thenReturn((long) 2);

		count = service.selectCount(category);

		verify(dao, times(1)).selectCount(category);
		verify(cachedCount, times(1)).get(category);
		verify(cachedCount, times(1)).put(category, count);
		Assert.assertThat(count, is((long) 2));
	}

	@Test
	public void selectOnePrice() {
		long id = 1;
		Product product = null;
		when(dao.selectOnePrice(id)).thenReturn(new Product().setId(id));

		product = service.selectOnePrice(id);

		verify(dao, times(1)).selectOnePrice(id);
		Assert.assertThat(product.getId(), is(id));
	}

}
