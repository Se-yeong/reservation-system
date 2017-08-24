package kr.or.reservation.service;

import kr.or.reservation.domain.User;

public interface UserService {
	public long insert(User user);

	public long update(User user);

	public User selectOneBySnsId(String snsId);
	
	public Boolean existByNaverId(String naverId);
}
