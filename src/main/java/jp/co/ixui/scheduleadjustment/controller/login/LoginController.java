package jp.co.ixui.scheduleadjustment.controller.login;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.scheduleadjustment.controller.event.EventController;
import jp.co.ixui.scheduleadjustment.service.UserService;

@Transactional
@Controller
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	EventController eventController;
	@Autowired
	HttpSession session;

	@RequestMapping(value="/" ,method = RequestMethod.GET)
	public String index() {
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

	@RequestMapping(value="/eventlist" ,method = RequestMethod.GET)
	public ModelAndView callList(ModelAndView mav){
		session.setAttribute("EmpInfo", new SignupForm("4336","ブラちゃん"));
		this.eventController.eventlist(mav);
		return mav;
	}
	
	@RequestMapping("/newuser")
	public ModelAndView newuser(ModelAndView mav) {
		mav.addObject("formModel", new SignupForm());
		mav.setViewName("newuser");
		return mav;
	}

}
