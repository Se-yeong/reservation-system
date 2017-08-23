package kr.or.reservation.controller.rest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.config.ServletContextConfig;
import kr.or.reservation.controller.CategoryController;
import kr.or.reservation.controller.rest.RestCategoryController;
import kr.or.reservation.domain.Category;
import kr.or.reservation.service.CategoryService;
import kr.or.reservation.service.impl.CategoryServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { RootApplicationContextConfig.class, ServletContextConfig.class })
public class RestCategoryControllerTest {

	private MockMvc mockMvc;

	@Mock
	CategoryService mockedCategoryService;

	RestCategoryController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		// mockMvc = webAppContextSetup(applicationContext).build();
		controller = new RestCategoryController(mockedCategoryService);
		mockMvc = standaloneSetup(controller).build();
	}

	@Test
	public void shouldSelectAll() {
		// give
		// service setup 부분을 별로의 함수 및 before 쪽에 두는게 맞을까요 ?  
		List<Category> list = new ArrayList<>();
		Category category = new Category().setName("names");
		list.add(category);
		when(mockedCategoryService.selectList()).thenReturn(list);

		// when
		List<Category> result = controller.selectList();

		// then
		verify(mockedCategoryService).selectList();
		assertEquals(result.get(0), category);
	}

	@Test
	public void shouldInsert() {
		// give
		long key;
		Category category = new Category();
		category.setName("장르");

		// when
		when(mockedCategoryService.insert(category)).thenReturn((long) 1);
		key = controller.insert(category);

		// then
		assertEquals(key, (long) 1);
		verify(mockedCategoryService).insert(category);
	}

	@Test
	public void shouldDelete() {
		// give
		long key = 1;
		long result = 0;

		// when
		when(mockedCategoryService.delete(key)).thenReturn((long) 1);
		result = controller.delete(key);

		// then
		assertEquals(result, key);
		verify(mockedCategoryService).delete(key);
	}

	@Test
	public void testCreate() throws Exception {

		// when - then
		mockMvc.perform(get("/api/category").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(mockedCategoryService).selectList();

	}
}
