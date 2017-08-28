package kr.or.reservation.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.dao.CommentImageDao;
import kr.or.reservation.domain.ReservationUserComment;
import kr.or.reservation.service.impl.CommentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class CommentServiceTest {

	@Mock
	CommentDao dao;

	@Mock
	CommentImageDao imagedao;
	
	@Autowired
	CommentService service;
	Logger log = Logger.getLogger(this.getClass());

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new CommentServiceImpl();
		service.setCommentDao(dao);
		service.setImagedao(imagedao);
	}

	@Test
	public void shouldInsert() {
		long inputLong = 1, returnLong;
		ReservationUserComment comment = new ReservationUserComment();
		comment.setComment("not null");
		when(dao.insert(comment)).thenReturn(inputLong);

		returnLong = service.insert(comment);

		Assert.assertThat(inputLong, is(returnLong));
		verify(dao, times(1)).insert(comment);
	}

	@Test
	public void shouldInsertForVaildCheck() {
		long inputLong = 1, returnLong = 0;
		ReservationUserComment comment = new ReservationUserComment();
		when(dao.insert(comment)).thenReturn(inputLong);

		try {
			returnLong = service.insert(comment);
			fail();
		} catch (IllegalArgumentException e) {
			// then
			Assert.assertThat((int) returnLong, is(0));
			verify(dao, times(0)).insert(comment);
		}
	}

	@Test
	public void shouldSelectList() {
		long productId = 1;
		int start = 1, amount = 1;
		when(dao.selectList(productId, start, amount)).thenReturn(new ArrayList());

		List list = service.selectList(productId, start, amount);

		verify(dao, times(1)).selectList(productId, start, amount);
		Assert.assertThat(list, not(nullValue()));
	}

	@Test
	public void shouldSelectListForVaildCheck() {
		long productId = -1;
		int start = 1, amount = 1;
		when(dao.selectList(productId, start, amount)).thenReturn(new ArrayList());

		List list = service.selectList(productId, start, amount);

		verify(dao, times(0)).selectList(productId, start, amount);
		Assert.assertThat(list, nullValue());
	}

	@Test
	public void shouldSelectImageList() {
		long id = 1;
		when(imagedao.selectList(id)).thenReturn(new ArrayList());

		List list = service.selectImageList(id);

		verify(imagedao, times(1)).selectList(id);
		Assert.assertThat(list, not(nullValue()));
	}
	
	@Test
	public void shouldSelectImageListForVaildCheck() {
		long id = -1;
		when(imagedao.selectList(id)).thenReturn(new ArrayList());

		List list = service.selectImageList(id);

		verify(imagedao, times(0)).selectList(id);
		Assert.assertThat(list, nullValue());
	}

}
