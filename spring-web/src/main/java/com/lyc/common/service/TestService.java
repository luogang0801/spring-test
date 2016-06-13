package com.lyc.common.service;

public interface TestService {
	public String test(String str, int userId) throws GlobalException;

	/**
	 * 
	 * @param user
	 * @return
	 * @throws GlobalException
	 * @exception
	 * @auth lc
	 * @date 2015年11月25日 下午5:36:55
	 */

	public void testInputStream(byte[] buffer);
}
