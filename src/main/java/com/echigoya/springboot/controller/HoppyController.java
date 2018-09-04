package com.echigoya.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.echigoya.springboot.domain.CategoryList;
import com.echigoya.springboot.domain.CommentList;
import com.echigoya.springboot.domain.EmpList;
import com.echigoya.springboot.domain.EventList;
import com.echigoya.springboot.domain.VoteInfoList;
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
    public ModelAndView eventdetalis(ModelAndView mav, @RequestParam int id){
    	List<EventList> eventListToId = this.hoppyService.getEventListeventListToId(id);
    	List<CategoryList> categoryList = this.hoppyService.getCategoryList();
    	List<VoteInfoList> voteinfoList = this.hoppyService.getVoteInfoList(id);
    	List<CommentList> commentList = this.hoppyService.getCommentList(id);
    	mav.setViewName("eventdetails");
    	mav.addObject("categoryList", categoryList);
    	mav.addObject("eventListToId", eventListToId);
    	mav.addObject("voteinfoList", voteinfoList);
    	mav.addObject("commentList", commentList);

        return  mav;
    }

    @RequestMapping("/eventlist")
    public ModelAndView eventlist(
			ModelAndView mav){
    	List<CategoryList> categoryList = this.hoppyService.getCategoryList();
    	List<EmpList> empList = this.hoppyService.getEmpList();
    	List<EventList> eventListNotDevision = this.hoppyService.getEventListNotDevision();
    	List<EventList> eventListDevision = this.hoppyService.getEventListDevision();
    	List<EventList> eventListEnd = this.hoppyService.getEventListEnd();
    	mav.setViewName("eventlist");
		mav.addObject("categoryList", categoryList);
		mav.addObject("empList", empList);
		mav.addObject("eventListNotDevision", eventListNotDevision);
		mav.addObject("eventListDevision", eventListDevision);
		mav.addObject("eventListEnd", eventListEnd);

        return mav;
    }


    @RequestMapping("/eventregist")
    public ModelAndView eventregist(ModelAndView mav){
    	List<CategoryList> categoryList = this.hoppyService.getCategoryList();
    	mav.addObject("categoryList", categoryList);
    	mav.setViewName("eventregist");
        return mav;
    }

    @RequestMapping("/newuser")
    public String newuser(){
        return "newuser";
    }



}
