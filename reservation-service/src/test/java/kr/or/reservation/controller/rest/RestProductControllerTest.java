package kr.or.reservation.controller.rest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.domain.Product;
import kr.or.reservation.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@WebAppConfiguration
@Transactional
public class RestProductControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	RestProductController controller;

	@Mock
	ProductService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new RestProductController(service);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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
	public void shouldSelectList_Context() throws Exception {
		int start = 1, amount = 3;
		long productId = 1;
		List<Product> list = new ArrayList<Product>();
		list.add(new Product().setId(productId));
		when(service.selectList(start, amount)).thenReturn(list);

		mockMvc.perform(get("/api/product?start=1&amount=3")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id").value((int) productId));
		verify(service, times(1)).selectList(start, amount);
	
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
	public void shouldSelectListByCategoryId_Context() throws Exception {
		long productId = 3;
		int categoryId = 1, start = 1, amount = 3;
		List<Product> list = new ArrayList<Product>();
		list.add(new Product().setId(productId));
		when(service.selectList(categoryId, start, amount)).thenReturn(list);

		mockMvc.perform(get("/api/product/category/1?start=1&amount=3")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id").value((int) productId));
		verify(service, times(1)).selectList(categoryId, start, amount);

	}

	@Test
	public void shouldSelectCount() {
		long count = 1;
		long result = 0;
		when(service.selectCount()).thenReturn(count);

		result = controller.selectCount();

		verify(service, times(1)).selectCount();
		Assert.assertThat(count, Is.is(result));

	}

	@Test
	public void shouldSelectCount_Context() throws Exception {
		long count = 4;
		when(service.selectCount()).thenReturn(count);

		mockMvc.perform(get("/api/product/count")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string("" + count));
		verify(service).selectCount();
	}

	@Test
	public void shouldSelectCountByCategory() {
		long count = 1;
		long result = 0;
		int categoryId = 1;
		when(service.selectCount(categoryId)).thenReturn(count);

		result = controller.selectCount(categoryId);

		verify(service, times(1)).selectCount(categoryId);
		Assert.assertThat(count, Is.is(result));

	}

	@Test
	public void shouldSelectCountByCategory_Context() throws Exception {
		long count = 4;
		long param = 1;
		when(service.selectCount(param)).thenReturn(count);

		mockMvc.perform(get("/api/product/count/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string("" + count));
		verify(service).selectCount(param);
	}

}
