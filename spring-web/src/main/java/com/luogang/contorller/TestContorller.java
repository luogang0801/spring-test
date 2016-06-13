package com.luogang.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luogang.test.TestService;

@Controller
public class TestContorller {
	@Autowired
	private TestService testService;

	@RequestMapping("test")
	public String test() {
		testService.testMethod();
		return "test";

	}
}
