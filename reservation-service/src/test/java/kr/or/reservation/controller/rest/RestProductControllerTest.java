package kr.or.reservation.controller.rest;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.reservation.common.Convert;
import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.domain.Product;
import kr.or.reservation.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class RestProductControllerTest {

	RestProductController controller;

	@Mock
	ProductService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new RestProductController(service);
	}

	@Test
	public void shouldSelectList() {

		int start = 0, amount = 3;
		List list = null;
		when(service.selectList(start, amount)).thenReturn(new ArrayList());

		list = controller.selectList(start, amount);

		verify(service, times(1)).selectList(start, amount);
		Assert.assertNotNull(list);
	}

	@Test
	public void shouldSelectListByCategoryId() {
		int start = 0, amount = 3, categoryId = 1;
		List list = null;
		when(service.selectList(categoryId, start, amount)).thenReturn(new ArrayList());

		list = controller.selectListByCategoryId(categoryId, start, amount);

		verify(service, times(1)).selectList(categoryId, start, amount);
		Assert.assertNotNull(list);
	}
	
	@Test
	public void shouldSelectCount() {
		long count = 1;
		long result = 0;
		when(service.selectCount()).thenReturn(count);
	
		result = controller.selectCount();
		
		verify(service,times(1)).selectCount();
		Assert.assertThat(count, is(result));
	
	}
	
	@Test
	public void shouldSelectCountByCategory() {
		long count = 1;
		long result = 0;
		int categoryId = 1;
		when(service.selectCount(categoryId)).thenReturn(count);
	
		result = controller.selectCount(categoryId);
		
		verify(service,times(1)).selectCount(categoryId);
		Assert.assertThat(count, is(result));
	
	}


}
