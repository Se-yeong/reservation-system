package kr.or.reservation.dao.impl;

import org.dbunit.DBTestCase;
import org.dbunit.PrepAndExpectedTestCase;
import org.dbunit.PrepAndExpectedTestCaseSteps;
import org.dbunit.VerifyTableDefinition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import kr.or.reservation.dao.CommentDao;

public class CommentDaoTest {
	
	CommentDao dao;

	@Mock
	private NamedParameterJdbcTemplate jdbc;

	@Before
	public void setUp() {
		dao = new CommentDaoImpl();

	}

	@After
	public void tearDown() {
	}


}
