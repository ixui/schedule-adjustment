package jp.co.ixui.scheduleadjustment.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.scheduleadjustment.domain.Category;
import jp.co.ixui.scheduleadjustment.domain.Comment;
import jp.co.ixui.scheduleadjustment.domain.Emp;
import jp.co.ixui.scheduleadjustment.domain.Event;
import jp.co.ixui.scheduleadjustment.domain.SearchForm;
import jp.co.ixui.scheduleadjustment.service.EventService;



@Controller
public class EventController {

	@Autowired
	EventService eventService;

	@RequestMapping(value="/" ,method = RequestMethod.GET)
    public String index(){

        return "index";
    }

	/*@RequestMapping(value="/" ,method = RequestMethod.POST)
    public String indexNewuser(){
		public ModelAndView index(@ModelAttribute("formModel") @Validated SignupForm signupForm,
				BindingResult result,
				ModelAndView mav){
			this.hoppyService.createUser(signupForm);
			mav.setViewName("index");
			return mav;
			    }

        return "index";
    }
    */

	@RequestMapping("/newuser")
    public String newuser(){
        return "newuser";
    }

	@RequestMapping(value="/eventlist" ,method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav){
    	List<Event> eventListNotDevision = this.eventService.getEventListNotDevision();
    	List<Event> eventListDevision = this.eventService.getEventListDevision();
    	List<Event> eventListEnd = this.eventService.getEventListEnd();
    	List<Category> categoryList = this.eventService.getCategoryList();
    	List<Emp> empList = this.eventService.getEmpList();
    	mav.setViewName("eventlist");
    	mav.addObject("categoryList", categoryList);
		mav.addObject("empList", empList);
    	mav.addObject("eventListNotDevision", eventListNotDevision);
		mav.addObject("eventListDevision", eventListDevision);
		mav.addObject("eventListEnd", eventListEnd);
        return mav;
    }

	@RequestMapping(value="/eventlist" ,method = RequestMethod.POST)
    public ModelAndView search(ModelAndView mav ,@ModelAttribute SearchForm searchForm,BindingResult result){
		List<Event> eventListNotDevision = this.eventService.getEventListNotDevision(searchForm);
    	List<Event> eventListDevision = this.eventService.getEventListDevision(searchForm);
    	List<Event> eventListEnd = this.eventService.getEventListEnd(searchForm);
    	List<Category> categoryList = this.eventService.getCategoryList();
    	List<Emp> empList = this.eventService.getEmpList();
    	mav.setViewName("eventlist");
    	mav.addObject("categoryList", categoryList);
		mav.addObject("empList", empList);
    	mav.addObject("eventListNotDevision", eventListNotDevision);
		mav.addObject("eventListDevision", eventListDevision);
		mav.addObject("eventListEnd", eventListEnd);
		return mav;
	}

	@RequestMapping("/eventdetails")
    public ModelAndView eventdetalis(ModelAndView mav, @RequestParam int id){
    	List<Event> eventListToId = this.eventService.getEventListeventListToId(id);
    	List<Category> categoryList = this.eventService.getCategoryList();
    	Map<Date, String> voteinfoList = this.eventService.getVoteInfoList(id);
    	List<Comment> commentList = this.eventService.getCommentList(id);
    	mav.setViewName("eventdetails");
    	mav.addObject("categoryList", categoryList);
    	mav.addObject("eventListToId", eventListToId);
    	mav.addObject("voteinfoList", voteinfoList);
    	mav.addObject("commentList", commentList);

        return  mav;
    }

	@RequestMapping("/eventregist")
    public ModelAndView eventregist(ModelAndView mav){
    	List<Category> categoryList = this.eventService.getCategoryList();
    	mav.addObject("categoryList", categoryList);
    	mav.setViewName("eventregist");
        return mav;
    }
}
