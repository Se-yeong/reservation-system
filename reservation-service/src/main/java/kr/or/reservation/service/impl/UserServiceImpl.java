package kr.or.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.UserDao;
import kr.or.reservation.domain.User;
import kr.or.reservation.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	UserDao dao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.dao = userDao;
	}
	@Override
	public long insert(User user) {
		return dao.insert(user);
	}
	@Override
	public long update(User user) {
		return dao.update(user);
	}
	@Override
	public User selectOneBySnsId(String snsId) {
		return dao.selectOneBySnsId(snsId);
	}
	@Override		
	public Boolean existByNaverId(String naverId) {
	  	if( dao.existByNaverId(naverId) == 0 ) {
    		return false;
    	} else {
    		return true;
    	}
	}
}
