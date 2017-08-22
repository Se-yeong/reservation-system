package kr.or.reservation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import kr.or.reservation.dao.CategoryDao;
import kr.or.reservation.domain.Category;
import kr.or.reservation.service.impl.CategoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CategoryTest {

	/*
	 * @Autowired CategoryService categoryService;
	 */
	// controller / dao  각 계층 테스트 할수 잇는 다른 방법 . 
	@Mock
	CategoryDao categoryDao;

	@Test
	public void testInsert() {
		// given
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.setCategoryDao(categoryDao);

		// when
		Category category = new Category();
		category.setName("영화");
		categoryService.insert(category);

		// then
		verify(categoryDao).insert(category);
	}

	@Test
	public void testSelect() {
		// given
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.setCategoryDao(categoryDao);

		// when
		categoryService.selectList();

		// then
		verify(categoryDao).selectList();
	}

	@Test
	public void testDelete() {
		// given
		long key = 1;
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.setCategoryDao(categoryDao);

		// when
		categoryService.delete(key);

		// then
		verify(categoryDao).delete(key);
	}

}
