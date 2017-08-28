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
import kr.or.reservation.dao.ReservationDao;
import kr.or.reservation.domain.Category;
import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.service.impl.CategoryServiceImpl;
import kr.or.reservation.service.impl.ReservationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ReservationSerivceTest {

	@Mock
	ReservationDao reservaionDao;

	ReservationService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new ReservationServiceImpl();
		service.setReservaioninfoDao(reservaionDao);
	}
	
	@Test
	public void shouldInsert() {
		long id = 1;
		long returnId;
		ReservationInfo reservationInfo = new ReservationInfo();
		when(reservaionDao.insert(reservationInfo)).thenReturn(id);

		returnId = service.insert(reservationInfo);
		
		verify(reservaionDao,times(1)).insert(reservationInfo);
		Assert.assertThat(id, is(returnId));
	}
	
	@Test
	public void shouldInsertForVaildCheck() {
		Long returnId;

		returnId = service.insert(null);
		
		verify(reservaionDao,times(0)).insert(null);
		Assert.assertThat(returnId, nullValue());
	}
	
	@Test
	public void shouldSelectOne() {
		long id = 1;
		ReservationInfo returnInfo = new ReservationInfo();
		ReservationInfo info = new ReservationInfo();
		info.setId(id);
		when(reservaionDao.selectOne(id)).thenReturn(info);
		
		returnInfo = service.selectOne(id);
		
		verify(reservaionDao,times(1)).selectOne(id);
		Assert.assertThat(info.getId(), is(returnInfo.getId()));
	}
}
