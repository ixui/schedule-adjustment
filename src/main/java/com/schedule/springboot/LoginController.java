package com.schedule.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
	
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView form(
			@RequestParam("loginMail")String mail,
			@RequestParam("loginPass")String pass,			
			ModelAndView mav) {
		
		mav.setViewName("index");		
		mav.addObject("mail", mail);
		mav.addObject("pass", pass);
		mav.addObject("checkForm",mail == "" || pass == "");
		mav.addObject("errorMsg", "未入力の項目があります。");
		
		return mav;
	}
}
