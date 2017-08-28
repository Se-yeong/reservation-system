package kr.or.reservation.dao.impl;

import java.io.FileInputStream;
import java.util.List;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.TestCase;
import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.domain.ReservationUserComment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class CommentDBTest extends TestCase {
	
	CommentDao dao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/reservationSystem/CommentDBTest;"
				+ "AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
	
		IDatabaseConnection connection = new DatabaseDataSourceConnection(dataSource);
		connection.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);		
		IDataSet dataSet = new XmlDataSet(
				new FileInputStream("src/test/resource/testData/commentDBTestData.xml"));
		
		this.dao = new CommentDaoImpl(dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	}
	
	@Test
	public void shouldSelectList() {
		System.out.println("test");
		List<ReservationUserComment> list = dao.selectList(1, 0, 3);
		System.out.println(list.get(0));
		assertNotNull(list);
		
	}

	
}