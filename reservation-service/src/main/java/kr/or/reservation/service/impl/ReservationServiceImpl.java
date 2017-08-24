package kr.or.reservation.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.ReservationDao;
import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	ReservationDao reservaionDao;

	@Autowired
	public void setReservaioninfoDao(ReservationDao reservaionDao) {
		this.reservaionDao = reservaionDao;
	}

	@Override
	public Long insert(ReservationInfo reservationInfo) {
		if (reservationInfo == null) {
			return null;
		}
		// default값으로 넣어둠. 
		reservationInfo.setReservationDate(new Timestamp(System.currentTimeMillis()));
		reservationInfo.setUserId(1);
		return reservaionDao.insert(reservationInfo);
	}
	
	@Override
	public ReservationInfo selectOne(long id) {
		return reservaionDao.selectOne(id);
	}

}
