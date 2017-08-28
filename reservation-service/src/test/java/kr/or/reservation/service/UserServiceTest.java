package kr.or.reservation.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import kr.or.reservation.dao.CategoryDao;
import kr.or.reservation.dao.UserDao;
import kr.or.reservation.domain.Category;
import kr.or.reservation.domain.User;
import kr.or.reservation.service.impl.CategoryServiceImpl;
import kr.or.reservation.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	/*
	 * @Autowired CategoryService categoryService;
	 */
	// controller / dao  각 계층 테스트 할수 잇는 다른 방법 . 
	@Mock
	UserDao dao;

	UserService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new UserServiceImpl();
		service.setUserDao(dao);
	}
	
	@Test
	public void shouldInsert() {
		long  value = 1, returnValue;
		User user = new User();
		when(dao.insert(user)).thenReturn(value);
		
		returnValue = service.insert(user);
		
		verify(dao,times(1)).insert(user);
		Assert.assertThat(returnValue, is(value));
	}
	
	@Test
	public void shouldUpdate() {
		long userId = 1;
		User user = new User();
		user.setId(userId);
		
		service.update(user);
		
		verify(dao,times(1)).update(user);
	}
	
	@Test
	public void shouldSelectOneBySnsId() {
		String snsId = "1";
		
		service.selectOneBySnsId(snsId);
		
		verify(dao,times(1)).selectOneBySnsId(snsId);
	}
	
	@Test
	public void shouldExistByNaverIdFalse() {
		String naverId = "0";
		boolean flag;
		when(dao.existByNaverId(naverId)).thenReturn(0);
		
		flag = service.existByNaverId(naverId);
		
		verify(dao,times(1)).existByNaverId(naverId);
		Assert.assertThat(flag, is(false));
	}
	
	@Test
	public void shouldExistByNaverIdTrue() {
		String naverId = "0";
		boolean flag;
		when(dao.existByNaverId(naverId)).thenReturn(1);
		
		flag = service.existByNaverId(naverId);
		
		verify(dao,times(1)).existByNaverId(naverId);
		Assert.assertThat(flag, is(true));
	}

}
