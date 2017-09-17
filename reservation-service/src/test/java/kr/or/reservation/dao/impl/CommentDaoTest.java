package kr.or.reservation.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
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
public class CommentDaoTest extends TestCase {
	
	CommentDao dao;
	
	IDataSet dataSet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		DataSource dataSource = TestDBConnect.getDataSource();
		IDatabaseConnection connection = new DatabaseDataSourceConnection(dataSource);
		connection.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);		
		dataSet = new XmlDataSet(
				new FileInputStream("src/test/resource/testData/commentDaoTestData.xml"));
		
		this.dao = new CommentDaoImpl(dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	}
	
	@Test
	@Transactional
	public void shouldSelectList() throws DataSetException {
		List<ReservationUserComment> list = dao.selectList(1, 0, 3);
		
		for(int i=0; i<3; i++) {
			String dataComment = list.get(i).getComment();
			String xmlDataComment = (String) dataSet.getTable("reservation_user_comment").getValue(i+1, "comment");
			
			assertThat(dataComment, is(xmlDataComment));
		}	
	}

	
}