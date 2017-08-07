package kr.or.reservation.dao;

import java.util.List;

import kr.or.reservation.domain.User;

public interface UserDao {
	public long insert(User user);

	public long update(User user);

	public User selectOne(long snsId);
}
