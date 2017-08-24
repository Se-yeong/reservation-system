package kr.or.reservation.service;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.Assert;
import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.dao.ReservationDao;
import kr.or.reservation.domain.ReservationInfo;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ReservationTest {
	
	@Autowired
	ReservationDao dao;
	Logger log = Logger.getLogger(this.getClass());

	@Test
	public void insertData() {
		ReservationInfo reservationInfo = new ReservationInfo();
		reservationInfo.setProductId(1);
		reservationInfo.setUserId(1);
		reservationInfo.setChildTicketCount(2);
		reservationInfo.setReservationName("장철운");
		reservationInfo.setReservationTel("000");
		reservationInfo.setReservationDate(new Timestamp(System.currentTimeMillis()));
		reservationInfo.setReservationType(0);
		reservationInfo.setTotalPrice(10000);
		
		long id = dao.insert(reservationInfo);
	}
}
