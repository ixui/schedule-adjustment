package jp.co.ixui.scheduleadjustment.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	public String index(){

		return "index";
	}

	@RequestMapping(value="/userregisted" ,method = RequestMethod.POST)
	public ModelAndView userregist(@ModelAttribute ("formModel")@Validated SignupForm signupForm,
				BindingResult result,
				ModelAndView mav){
		if (result.hasErrors()){
				this.userService.createUser(signupForm);
				mav.setViewName("index");
				return mav;
			}
				mav.setViewName("newuser");
				return mav;
	}


	@RequestMapping("/newuser")
	public ModelAndView newuser(ModelAndView mav){
		mav.addObject("formModel", new SignupForm());
		mav.setViewName("newuser");
		return mav;
	}

}
