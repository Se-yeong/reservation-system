package kr.or.reservation.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.domain.Category;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class CategoryTest {

	@Autowired
	CategoryService categoryService;

	private long key;

	@Before
	public void insert() {
		Category category = new Category();
		category.setName("영화");
		key = categoryService.insert(category);
	}

	@Test
	public void select() {
		List<Category> categories = categoryService.selectList();
		int last = categories.size() - 1;
		long selectKey = categories.get(last).getId();
		Assert.assertThat(selectKey, is(key));
	}

	@Test
	public void delete() {
		long index = categoryService.delete(key);
		Assert.assertThat(index, is((long)1));
	}

}
