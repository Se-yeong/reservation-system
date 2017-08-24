package kr.or.reservation.dao;

import kr.or.reservation.domain.FileDomain;

public interface FileDao {
	public int[] insertArray(FileDomain[] files);
}
