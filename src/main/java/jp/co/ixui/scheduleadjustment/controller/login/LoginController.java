package jp.co.ixui.scheduleadjustment.controller.login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.scheduleadjustment.service.UserService;

@Transactional
@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value="/" ,method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute(new SignupForm());
		return "index";
	}
	
	@RequestMapping(value = "/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "index";
	}

	@RequestMapping(value="/userregisted" ,method = RequestMethod.POST)
	public ModelAndView userRegist(@ModelAttribute("formModel") @Validated SignupForm signupForm,	
				BindingResult result,
				ModelAndView mav) throws Exception,IOException{
		if (result.hasErrors()) {
			mav.setViewName("newuser");
			return mav;
		}
		
		this.userService.createUser(signupForm);
		mav.setViewName("index");
		mav.addObject("dbregist","success");
		
		return mav;
	}

	
	
	@RequestMapping("/newuser")
	public ModelAndView newuser(ModelAndView mav) {
		mav.addObject("formModel", new SignupForm());
		mav.setViewName("newuser");
		return mav;
	}

}
