package kr.or.reservation.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class CommentTest {
	
	@Autowired
	CommentService commentService;
	Logger log =  Logger.getLogger(this.getClass());
	
	@Test
	public void vaildTest() {
		log.info(commentService.selectList(0, 1, 1));
	}
}