package kr.or.reservation.controller.rest;

import org.junit.Before;
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
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { RootApplicationContextConfig.class, ServletContextConfig.class })
public class CategoryControllerTest {

	private MockMvc mockMvc;

	@Mock
	CategoryService mockedCategoryService;

	@Autowired
	WebApplicationContext applicationContext;

	RestCategoryController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = webAppContextSetup(applicationContext).build();
		controller = new RestCategoryController(mockedCategoryService);
	}

	@Test
	public void shouldSelectAll() {
		// give
		RestCategoryController controller = new RestCategoryController(mockedCategoryService);

		// when
		controller.selectList();

		// then
		verify(mockedCategoryService).selectList();
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
		long returnedKey = 0;
		Category category = new Category();
		category.setName("장르");

		// when
		when(mockedCategoryService.delete(key)).thenReturn((long) 1);
		returnedKey = controller.delete(key);

		// then
		assertEquals(returnedKey, key);
		verify(mockedCategoryService).delete(key);
	}

	@Test
	public void testCreate() throws Exception {
		// service 부분 Mock을 주입 할 수없나 ?
		mockMvc.perform(get("/api/category").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
