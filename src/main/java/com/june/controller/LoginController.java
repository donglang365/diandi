package com.june.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.june.bean.User;
import com.june.service.UserService;
import com.june.util.MD5Util;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("login")
	public ModelAndView loginIndex() {
		
		return new ModelAndView("Wopop");
	}
	
	@RequestMapping("doLogin")
	public ModelAndView doLogin(String username,String password) {
		User user = new User();
		user.setPassword(MD5Util.MD5(password));
		user.setUserName(username);
		User result=userService.find(user);
		if(result==null) {
			ModelAndView mv =new ModelAndView("Wopop");
			mv.addObject("failed", "用户名或密码错误！");
			return mv;
		}
		return new ModelAndView("index");
	}
	
	@RequestMapping("register")
	@ResponseBody
	public String register(String username,String password) {
		User user = userService.findUserByName(username);
		if (user!=null) {
			return "用户已存在！";
		}
		user = new User();
		user.setUserName(username);
		user.setPassword(MD5Util.MD5(password));
		userService.insert(user);
		return user.getUserName();
	} 
	
}
