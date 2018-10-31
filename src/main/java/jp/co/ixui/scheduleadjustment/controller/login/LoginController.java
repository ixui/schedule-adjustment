package jp.co.ixui.scheduleadjustment.controller.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ixui.scheduleadjustment.service.UserService;

@Transactional
@Controller
public class LoginController {

	@Autowired
	UserService userService;

	public static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	
	/**
	 * NullPointerExceptionの例外処理
	 * 
	 */
	@ExceptionHandler(NullPointerException.class)
	public String NullPointerExceptionHandler() {
		log.debug("NullPointerExceptionが発生しました。");
		return "err";
	}

	/**
	 * SQLExceptionの例外処理
	 * @param e
	 * @throws Exception
	 */
	@ExceptionHandler(SQLException.class)
	private String sqlExceptionHandler(Exception e) {
		log.debug("SQLExceptionが発生しました。");
		return "err";
	}

	/**
	 * Runtime系の例外処理
	 * @param e
	 * @throws RuntimeException
	 */
	@ExceptionHandler(RuntimeException.class)
	private String runtimeExceptionHandler(RuntimeException e) {
		log.debug("RuntimeExceptionが発生しました。");
		return "err";
	}

	
	/**
	 * その他のException例外処理
	 * @param e
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	private String exceptionHandler(Exception e) {
		log.debug("何らかの例外が発生しました。");
		return "err";
	}
	
	
	
	@RequestMapping(value="/" ,method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		if (this.userService.isValidUserSession(request)) {
			return "redirect:/eventlist";
		}
		return "index";
	}
	
	@RequestMapping(value = "/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "index";
	}

	@RequestMapping(value="/userregisted" ,method = RequestMethod.POST)
	public String userRegist(@ModelAttribute("formModel") @Validated SignupForm signupForm,	
				BindingResult result,RedirectAttributes attributes) throws Exception,IOException{
		if (result.hasErrors()) {
			return "newuser";
		}
		
		this.userService.createUser(signupForm);
		attributes.addFlashAttribute("dbregist","success");
		
		return "redirect:/";
	}

	
	
	@RequestMapping("/newuser")
	public ModelAndView newuser(ModelAndView mav){
		mav.addObject("formModel", new SignupForm());
		mav.setViewName("newuser");
		return mav;
	}

}
