package kr.or.reservation.service;

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
import org.springframework.web.context.WebApplicationContext;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.config.ServletContextConfig;
import kr.or.reservation.controller.CategoryController;
import kr.or.reservation.controller.rest.RestCategoryController;
import kr.or.reservation.service.impl.CategoryServiceImpl;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { RootApplicationContextConfig.class , ServletContextConfig.class})
public class CategoryControllerTest {

	private MockMvc mockMvc;

	@Mock
	CategoryService mockedCategoryService;

	@Autowired
	WebApplicationContext applicationContext;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	            MediaType.APPLICATION_JSON.getSubtype(),
	            Charset.forName("utf8"));

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = webAppContextSetup(applicationContext).build();

	}

	// Controller Layer 만 Test
	@Test
	public void testController() {
		// give
		RestCategoryController controller = new RestCategoryController(mockedCategoryService);

		// when
		controller.selectList();

		// then
		verify(mockedCategoryService).selectList();
	}

	// 이걸 뭐라 부를까요 ~~ ? 
	@Test
	public void testCreate() throws Exception {
		this.mockMvc
				.perform(get("/api/category").contentType(contentType))
						.andExpect(status().isOk())
						.andExpect();
	}

}
