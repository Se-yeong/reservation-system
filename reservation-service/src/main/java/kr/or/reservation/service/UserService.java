package kr.or.reservation.service;

import kr.or.reservation.dao.UserDao;
import kr.or.reservation.domain.User;

public interface UserService {
	public void setUserDao(UserDao userDao) ;
	
	public long insert(User user);

	public long update(User user);

	public User selectOneBySnsId(String snsId);
	
	public Boolean existByNaverId(String naverId);
}
