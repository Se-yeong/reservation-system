package kr.or.reservation.service;

import kr.or.reservation.domain.ReservationInfo;

public interface ReservationService {
	
	public Long insert(ReservationInfo reservationInfo);
	public ReservationInfo selectOne(long id);
}
