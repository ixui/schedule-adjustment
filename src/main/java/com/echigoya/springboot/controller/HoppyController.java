package com.echigoya.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.echigoya.springboot.domain.EventList;
import com.echigoya.springboot.service.HoppyService;

@Controller
public class HoppyController {

	@Autowired
	HoppyService hoppyService;

	@RequestMapping(value="/" ,method = RequestMethod.GET)
    public String index(){

        return "index";
    }

	/*@RequestMapping(value="/newuser",method = RequestMethod.POST)
    public ModelAndView index(@ModelAttribute("formModel") @Validated SignupForm signupForm,
			BindingResult result,
			ModelAndView mav){
		this.hoppyService.createUser(signupForm);
		mav.setViewName("index");
		return mav;
		    }
		    */


    @RequestMapping("/eventdetails")
    public String eventdetalis(){

        return "eventdetails";
    }

    @RequestMapping("/eventlist")
    public ModelAndView eventlist(
			ModelAndView mav){
    	List<EventList> eventList = this.hoppyService.getEventList();
		mav.setViewName("eventlist");
		mav.addObject("eventList", eventList);
        return mav;
    }

    @RequestMapping("/eventregist")
    public String eventregist(){
        return "eventregist";
    }

    @RequestMapping("/newuser")
    public String newuser(){
        return "newuser";
    }



}
