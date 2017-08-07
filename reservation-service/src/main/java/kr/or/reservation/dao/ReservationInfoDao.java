package kr.or.reservation.dao;

import java.util.List;

import kr.or.reservation.domain.ReservationInfo;

public interface ReservationInfoDao {
	public long insert(ReservationInfo reservationInfo);

	public long update(ReservationInfo reservationInfo);

	public List<ReservationInfo> selectList(long userId);
}
