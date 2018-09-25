package jp.co.ixui.scheduleadjustment.controller.event;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import jp.co.ixui.scheduleadjustment.domain.Search;
import jp.co.ixui.scheduleadjustment.service.EventService;



@Controller
public class EventController {

	@Autowired
	EventService eventService;

	public static Logger log = LoggerFactory.getLogger(EventController.class);

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
		log.debug("あああ");
        return mav;
    }

	@RequestMapping(value="/eventlistregist" ,method = RequestMethod.POST)
	public ModelAndView eventregist(@ModelAttribute EventRegistForm eventregistForm,
			BindingResult result,
			ModelAndView mav){
			this.eventService.createEvent(eventregistForm);
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
    public ModelAndView search(ModelAndView mav ,@ModelAttribute SearchForm searchForm,
    		BindingResult result){
		List<Event> eventListNotDevision = this.eventService.getEventListNotDevision(searchForm);
    	List<Event> eventListDevision = this.eventService.getEventListDevision(searchForm);
    	List<Event> eventListEnd = this.eventService.getEventListEnd(searchForm);
    	Search search =this.eventService.getSearchCondition(searchForm);
    	List<Category> categoryList = this.eventService.getCategoryList();
    	List<Emp> empList = this.eventService.getEmpList();

    	mav.setViewName("eventlist");
    	mav.addObject("categoryList", categoryList);
		mav.addObject("empList", empList);
		mav.addObject("search", search);
    	mav.addObject("eventListNotDevision", eventListNotDevision);
		mav.addObject("eventListDevision", eventListDevision);
		mav.addObject("eventListEnd", eventListEnd);

		return mav;
	}



	@RequestMapping(value="/eventdetails" ,method = RequestMethod.GET)
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

	@RequestMapping(value="/commentregist" ,method = RequestMethod.POST)
	public ModelAndView commentregist(@ModelAttribute CommentForm commentForm,
			BindingResult result,
			ModelAndView mav){
			this.eventService.commentRegist(commentForm);
			List<Event> eventListToId = this.eventService.getEventListeventListToId(commentForm.getEventId());
	    	List<Category> categoryList = this.eventService.getCategoryList();
	    	Map<Date, String> voteinfoList = this.eventService.getVoteInfoList(commentForm.getEventId());
	    	List<Comment> commentList = this.eventService.getCommentList(commentForm.getEventId());
			mav.setViewName("eventdetails");
			mav.addObject("categoryList", categoryList);
	    	mav.addObject("eventListToId", eventListToId);
	    	mav.addObject("voteinfoList", voteinfoList);
	    	mav.addObject("commentList", commentList);
			return  mav;
    }

	@RequestMapping(value="/eventdetails" ,method = RequestMethod.POST)
	public ModelAndView eventreturn(@ModelAttribute EventRegistForm eventForm,
			BindingResult result,
			ModelAndView mav){
			this.eventService.detailsRedisplay(eventForm);
			List<Event> eventListToId = this.eventService.getEventListeventListToId(eventForm.getEventId());
	    	List<Category> categoryList = this.eventService.getCategoryList();
	    	Map<Date, String> voteinfoList = this.eventService.getVoteInfoList(eventForm.getEventId());
	    	List<Comment> commentList = this.eventService.getCommentList(eventForm.getEventId());
			mav.setViewName("eventdetails");
			mav.addObject("categoryList", categoryList);
	    	mav.addObject("eventListToId", eventListToId);
	    	mav.addObject("voteinfoList", voteinfoList);
	    	mav.addObject("commentList", commentList);
			return  mav;
    }

	@RequestMapping(value="/eventditailsdecide",method = RequestMethod.POST)
	public ModelAndView decided(ModelAndView mav ,@ModelAttribute VoteForm voteForm,
			BindingResult result){
		this.eventService.decidedDay(voteForm);
		List<Event> eventListToId = this.eventService.getEventListeventListToId(voteForm.getEventId());
    	List<Category> categoryList = this.eventService.getCategoryList();
    	Map<Date, String> voteinfoList = this.eventService.getVoteInfoList(voteForm.getEventId());
    	List<Comment> commentList = this.eventService.getCommentList(voteForm.getEventId());
		mav.setViewName("eventdetails");
		mav.addObject("categoryList", categoryList);
    	mav.addObject("eventListToId", eventListToId);
    	mav.addObject("voteinfoList", voteinfoList);
    	mav.addObject("commentList", commentList);
		return mav;
	}

	@RequestMapping(value="/voteregist",method = RequestMethod.POST)
	public ModelAndView vote(ModelAndView mav ,@ModelAttribute VoteForm voteForm,
			BindingResult result){
		this.eventService.voteDay(voteForm);
		List<Event> eventListToId = this.eventService.getEventListeventListToId(voteForm.getEventId());
    	List<Category> categoryList = this.eventService.getCategoryList();
    	Map<Date, String> voteinfoList = this.eventService.getVoteInfoList(voteForm.getEventId());
    	List<Comment> commentList = this.eventService.getCommentList(voteForm.getEventId());
		mav.setViewName("eventdetails");
		mav.addObject("categoryList", categoryList);
    	mav.addObject("eventListToId", eventListToId);
    	mav.addObject("voteinfoList", voteinfoList);
    	mav.addObject("commentList", commentList);
		return mav;
	}

	@RequestMapping(value="/eventditailsupdate",method = RequestMethod.POST)
	public ModelAndView update(ModelAndView mav ,@ModelAttribute EventRegistForm eventregistForm,
			BindingResult result){
		this.eventService.eventUpdate(eventregistForm);
		List<Event> eventListToId = this.eventService.getEventListeventListToId(eventregistForm.getEventId());
    	List<Category> categoryList = this.eventService.getCategoryList();
    	Map<Date, String> voteinfoList = this.eventService.getVoteInfoList(eventregistForm.getEventId());
    	List<Comment> commentList = this.eventService.getCommentList(eventregistForm.getEventId());
		mav.setViewName("eventdetails");
		mav.addObject("categoryList", categoryList);
    	mav.addObject("eventListToId", eventListToId);
    	mav.addObject("voteinfoList", voteinfoList);
    	mav.addObject("commentList", commentList);
		return mav;
	}

	@RequestMapping(value="/eventlistdelieted",method = RequestMethod.POST)
	public ModelAndView delete(ModelAndView mav ,@ModelAttribute EventRegistForm eventregistForm,
			BindingResult result){
		this.eventService.eventDelete(eventregistForm);
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

	@RequestMapping(value="/eventregist",method=RequestMethod.GET)
    public ModelAndView eventregist(ModelAndView mav){
    	List<Category> categoryList = this.eventService.getCategoryList();
    	mav.addObject("categoryList", categoryList);
    	mav.setViewName("eventregist");
        return mav;
    }
}