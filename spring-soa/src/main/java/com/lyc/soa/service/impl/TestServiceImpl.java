package com.lyc.soa.service.impl;

import org.springframework.stereotype.Service;

import com.luogang.test.TestService;

@Service("soa.test")
public class TestServiceImpl implements TestService {

	@Override
	public void testMethod() {
		System.out.println("test");
	}

}
