package jp.co.ixui.scheduleadjustment.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.scheduleadjustment.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value="/" ,method = RequestMethod.GET)
    public String index(){

        return "index";
    }

	@RequestMapping(value="/" ,method = RequestMethod.POST)
    public ModelAndView userregist(@ModelAttribute SignupForm signupForm,
				BindingResult result,
				ModelAndView mav){
		this.userService.createUser(signupForm);
			mav.setViewName("index");
			return mav;
	}


	@RequestMapping("/newuser")
    public String newuser(){
        return "newuser";
    }

}
