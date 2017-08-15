package com.june.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.june.bean.User;
import com.june.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/showUser2")
	public ModelAndView toUser2(String name) {
		ModelAndView mv = new ModelAndView("user");
		userService.findUserByName(name);
		List<User> users=new ArrayList<User>();
		User user=null;
		System.out.println(System.currentTimeMillis());
		for(int i=0;i<14400;i++){
			for(int j=0;j<7;j++){
				user=new User();
				user.setAge(i);
				user.setUserName("1");
			}
		}
		System.out.println(System.currentTimeMillis());
		return mv;
	}
	
	@RequestMapping("/showUser")
	@ResponseBody
	public List<User> toUser(String name) {
		ModelAndView mv = new ModelAndView("user");
		List<User> users=new ArrayList<User>();
		User user=null;
		String strDate="201703100000";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
		
		Calendar calendar=Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		System.out.println(System.currentTimeMillis());
		System.out.println(calendar.getTime());
		int times=24*60*8;
		for(int i=0;i<times;i++){
			user=new User();
			user.setAge(i);
			user.setUserName("1");
			users.add(user);
			calendar.add(Calendar.MINUTE, 1);
		}
		System.out.println(calendar.getTime());
		System.out.println(System.currentTimeMillis());
		return users;
	}
}
