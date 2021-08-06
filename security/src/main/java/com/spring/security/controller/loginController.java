package com.spring.security.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class loginController {

	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping("/courses")
	public String courses()
	{
		return "courses";
	}
}
