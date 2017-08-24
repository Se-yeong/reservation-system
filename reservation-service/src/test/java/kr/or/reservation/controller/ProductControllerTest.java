package kr.or.reservation.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
public class ProductControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	ProductController controller;

	@Mock
	ProductService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new ProductController();
		controller.setProductService(service);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void shuoldViewMain() throws Exception {
		long productId = 1;
		Product product = new Product().setId(productId);
		when(service.selectOne(productId)).thenReturn(product);

		mockMvc.perform(get("/product/" + productId)).andExpect(status().isOk())
				.andExpect(model().attributeExists("product")).andExpect(model().attribute("product", product))
				.andExpect(view().name("detail"));

		verify(service, times(1)).selectOne(productId);
	}

	@Test
	public void shouldViewReserve() throws Exception {
		long productId = 1;
		Product product = new Product().setId(productId);
		when(service.selectOnePrice(productId)).thenReturn(product);

		mockMvc.perform(get("/product/" + productId + "/reservation")).andExpect(status().isOk())
				.andExpect(model().attributeExists("product")).andExpect(model().attribute("product", product))
				.andExpect(view().name("reserve"));

		verify(service, times(1)).selectOnePrice(productId);
	}
}
