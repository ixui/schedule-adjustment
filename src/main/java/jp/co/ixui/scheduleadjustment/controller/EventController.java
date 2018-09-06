package jp.co.ixui.scheduleadjustment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.scheduleadjustment.domain.Category;
import jp.co.ixui.scheduleadjustment.domain.Comment;
import jp.co.ixui.scheduleadjustment.domain.Emp;
import jp.co.ixui.scheduleadjustment.domain.Event;
import jp.co.ixui.scheduleadjustment.domain.VoteInfo;
import jp.co.ixui.scheduleadjustment.service.EventService;



@Controller
public class EventController {

	@Autowired
	EventService eventService;

	@RequestMapping(value="/" ,method = RequestMethod.GET)
    public String index(){

        return "index";
    }

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

    	System.out.println(eventListNotDevision);
    	System.out.println(eventListDevision);
    	System.out.println(eventListEnd);
    	System.out.println("こちら本物です。");
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
    	List<VoteInfo> voteinfoList = this.eventService.getVoteInfoList(id);
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
