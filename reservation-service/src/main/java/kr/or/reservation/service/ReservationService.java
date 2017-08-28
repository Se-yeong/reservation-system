package kr.or.reservation.service;

import kr.or.reservation.dao.ReservationDao;
import kr.or.reservation.domain.ReservationInfo;

public interface ReservationService {
	public void setReservaioninfoDao(ReservationDao reservaionDao);
	
	public Long insert(ReservationInfo reservationInfo);
	public ReservationInfo selectOne(long id);
}
