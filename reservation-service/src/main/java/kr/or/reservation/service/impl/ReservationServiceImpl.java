package kr.or.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.ReservationInfoDao;
import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	ReservationInfoDao reservaioninfoDao;

	@Autowired
	public void setReservaioninfoDao(ReservationInfoDao reservaioninfoDao) {
		this.reservaioninfoDao = reservaioninfoDao;
	}

	@Override
	public Long insert(ReservationInfo reservationInfo) {
		if (reservationInfo == null) {
			return null;
		}
		return reservaioninfoDao.insert(reservationInfo);
	}

}
