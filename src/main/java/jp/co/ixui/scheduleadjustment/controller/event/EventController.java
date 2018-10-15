package jp.co.ixui.scheduleadjustment.controller.event;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.scheduleadjustment.controller.login.SignupForm;
import jp.co.ixui.scheduleadjustment.domain.Category;
import jp.co.ixui.scheduleadjustment.domain.Checked;
import jp.co.ixui.scheduleadjustment.domain.Comment;
import jp.co.ixui.scheduleadjustment.domain.Emp;
import jp.co.ixui.scheduleadjustment.domain.Event;
import jp.co.ixui.scheduleadjustment.domain.Search;
import jp.co.ixui.scheduleadjustment.service.EventService;



@Controller
public class EventController {

	@Autowired
	EventService eventService;
	@Autowired
	HttpSession session;

	public static Logger log = LoggerFactory.getLogger(EventController.class);

	/**
	 * イベント一覧表示
	 *
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/eventlists" ,method = RequestMethod.GET)
	public ModelAndView eventlist(ModelAndView mav){
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
		mav.addObject("formModel", new SearchForm());
		return mav;
	}

	/**
	 * イベント一覧表示（イベント登録後）
	 *
	 * @param eventregistForm
	 * @param result
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/eventlistregist", method = RequestMethod.POST)
	public ModelAndView eventregist(@ModelAttribute ("formModel") @Validated EventRegistForm eventregistForm,
			BindingResult result,
			ModelAndView mav){
		if (result.hasErrors()) {
			SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
			List<Category> categoryList = this.eventService.getCategoryList();
			mav.addObject("empInfo",user);
			mav.addObject("categoryList", categoryList);
			mav.setViewName("eventregist");
			return mav;
		}
		
		this.eventService.createEvent(eventregistForm);
		
		List<Event> eventListNotDevision = this.eventService.getEventListNotDevision();
		List<Event> eventListDevision = this.eventService.getEventListDevision();
		List<Event> eventListEnd = this.eventService.getEventListEnd();
		List<Category> categoryList = this.eventService.getCategoryList();
		List<Emp> empList = this.eventService.getEmpList();
		
		mav.addObject("categoryList", categoryList);
		mav.addObject("empList", empList);
		mav.addObject("eventListNotDevision", eventListNotDevision);
		mav.addObject("eventListDevision", eventListDevision);
		mav.addObject("eventListEnd", eventListEnd);
		mav.addObject("formModel", new SearchForm());
		mav.addObject("dbregist","success");
		mav.setViewName("eventlist");
		
		return mav;
	}
	

	/**
	 * イベント一覧表示（詳細検索の結果）
	 * 
	 * @param searchForm
	 * @param result
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/eventlist" ,method = RequestMethod.POST)
	public ModelAndView search(ModelAndView mav ,@ModelAttribute("formModel") @Validated SearchForm searchForm,
			BindingResult result){
		if (result.hasErrors()) {
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
	
	/**
	 * イベント詳細表示
	 *
	 * @param id イベントID
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/eventdetails" ,method = RequestMethod.GET)
	public ModelAndView eventdetalis(ModelAndView mav, @RequestParam int id){
		List<Event> eventListToId = this.eventService.getEventListeventListToId(id);
		List<Category> categoryList = this.eventService.getCategoryList();
		SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
		List<Checked> voteinfoList = this.eventService.getVoteInfoList(id,user);
		List<Comment> commentList = this.eventService.getCommentList(id);

		mav.addObject("empInfo",user);
		mav.setViewName("eventdetails");
		mav.addObject("categoryList", categoryList);
		mav.addObject("eventListToId", eventListToId);
		mav.addObject("voteinfoList", voteinfoList);
		mav.addObject("commentList", commentList);
		mav.addObject("commentModel", new CommentForm());
		mav.addObject("voteModel",new VoteForm());
		mav.addObject("eventModel",new EventRegistForm());

		return  mav;
	}

	/**
	 * イベント詳細（コメント登録後）
	 *
	 * @param commentForm
	 * @param result
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/commentregist" ,method = RequestMethod.POST)
	public ModelAndView commentregist(@ModelAttribute ("commentModel") @Validated CommentForm commentForm,
			BindingResult result,
			ModelAndView mav){
		if (result.hasErrors()){
			List<Event> eventListToId = this.eventService.getEventListeventListToId(commentForm.getEventId());
			List<Category> categoryList = this.eventService.getCategoryList();
			List<Comment> commentList = this.eventService.getCommentList(commentForm.getEventId());
			SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
			List<Checked> voteinfoList = this.eventService.getVoteInfoList(commentForm.getEventId(),user);
			
			mav.addObject("empInfo",user);			
			mav.setViewName("eventdetails");
			mav.addObject("categoryList", categoryList);
			mav.addObject("eventListToId", eventListToId);
			mav.addObject("voteinfoList", voteinfoList);
			mav.addObject("commentList", commentList);
			mav.addObject("voteModel",new VoteForm());
			mav.addObject("eventModel",new EventRegistForm());
			return mav;
			}
			SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
			this.eventService.commentRegist(commentForm,user);
			List<Event> eventListToId = this.eventService.getEventListeventListToId(commentForm.getEventId());
			List<Category> categoryList = this.eventService.getCategoryList();
			List<Checked> voteinfoList = this.eventService.getVoteInfoList(commentForm.getEventId(),user);
			List<Comment> commentList = this.eventService.getCommentList(commentForm.getEventId());
			
			mav.addObject("empInfo",user);
			mav.setViewName("eventdetails");
			mav.addObject("categoryList", categoryList);
			mav.addObject("eventListToId", eventListToId);
			mav.addObject("voteinfoList", voteinfoList);
			mav.addObject("commentList", commentList);
			mav.addObject("commentModel", new CommentForm());
			mav.addObject("voteModel",new VoteForm());
			mav.addObject("eventModel",new EventRegistForm());
			
		return mav;
	}

	/**
	 * イベント詳細（開催日決定後）
	 *
	 * @param voteForm
	 * @param result
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/eventditailsdecide",method = RequestMethod.POST)
	public ModelAndView decided(ModelAndView mav, @ModelAttribute("voteModel") @Validated VoteForm voteForm,
			BindingResult result){
		if (result.hasErrors()) {
			List<Event> eventListToId = this.eventService.getEventListeventListToId(voteForm.getEventId());
			List<Category> categoryList = this.eventService.getCategoryList();
			List<Comment> commentList = this.eventService.getCommentList(voteForm.getEventId());
			SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
			List<Checked> voteinfoList = this.eventService.getVoteInfoList(voteForm.getEventId(),user);
			
			mav.addObject("empInfo",user);
			mav.setViewName("eventdetails");
			mav.addObject("categoryList", categoryList);
			mav.addObject("eventListToId", eventListToId);
			mav.addObject("voteinfoList", voteinfoList);
			mav.addObject("commentList", commentList);
			mav.addObject("commentModel", new CommentForm());
			mav.addObject("eventModel",new EventRegistForm());
			return mav;
		}
		
		this.eventService.decidedDay(voteForm);
		List<Event> eventListToId = this.eventService.getEventListeventListToId(voteForm.getEventId());
		List<Category> categoryList = this.eventService.getCategoryList();
		List<Comment> commentList = this.eventService.getCommentList(voteForm.getEventId());
		SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
		List<Checked> voteinfoList = this.eventService.getVoteInfoList(voteForm.getEventId(),user);
		
		mav.addObject("empInfo",user);
		mav.setViewName("eventdetails");
		mav.addObject("categoryList", categoryList);
		mav.addObject("eventListToId", eventListToId);
		mav.addObject("voteinfoList", voteinfoList);
		mav.addObject("commentList", commentList);
		mav.addObject("commentModel", new CommentForm());
		mav.addObject("voteModel",new VoteForm());
		mav.addObject("eventModel",new EventRegistForm());
		mav.addObject("dbregist","success");
		return mav;
	}

	/**
	 * イベント詳細（投票後）
	 *
	 * @param voteForm
	 * @param result
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/voteregist",method = RequestMethod.POST)
	public ModelAndView vote(ModelAndView mav ,@ModelAttribute VoteForm voteForm,
			BindingResult result){
		SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
		this.eventService.voteDay(voteForm,user);
		List<Event> eventListToId = this.eventService.getEventListeventListToId(voteForm.getEventId());
		List<Category> categoryList = this.eventService.getCategoryList();
		List<Checked> voteinfoList = this.eventService.getVoteInfoList(voteForm.getEventId(),user);
		List<Comment> commentList = this.eventService.getCommentList(voteForm.getEventId());
		
		mav.addObject("empInfo",user);
		mav.setViewName("eventdetails");
		mav.addObject("categoryList", categoryList);
		mav.addObject("eventListToId", eventListToId);
		mav.addObject("voteinfoList", voteinfoList);
		mav.addObject("commentList", commentList);
		mav.addObject("commentModel", new CommentForm());
		mav.addObject("voteModel",new VoteForm());
		mav.addObject("eventModel",new EventRegistForm());
		mav.addObject("dbregist","success");
		return mav;
	}

	/**
	 * イベント詳細（詳細変更後）
	 *
	 * @param eventregistForm
	 * @param result
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/eventditailsupdate",method = RequestMethod.POST)
	public ModelAndView update(ModelAndView mav ,@ModelAttribute("eventModel") @Validated EventRegistForm eventregistForm,
			BindingResult result){
		if (result.hasErrors()) {
			List<Event> eventListToId = this.eventService.getEventListeventListToId(eventregistForm.getEventId());
			List<Category> categoryList = this.eventService.getCategoryList();
			List<Comment> commentList = this.eventService.getCommentList(eventregistForm.getEventId());
			SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
			List<Checked> voteinfoList = this.eventService.getVoteInfoList(eventregistForm.getEventId(),user);
			
			mav.addObject("empInfo",user);
			mav.setViewName("eventdetails");
			mav.addObject("categoryList", categoryList);
			mav.addObject("eventListToId", eventListToId);
			mav.addObject("voteinfoList", voteinfoList);
			mav.addObject("commentList", commentList);
			mav.addObject("commentModel", new CommentForm());
			mav.addObject("voteModel",new VoteForm());
			return mav;
		}
		
		
		this.eventService.eventUpdate(eventregistForm);
		this.eventService.candidateUpdate(eventregistForm);
		List<Event> eventListToId = this.eventService.getEventListeventListToId(eventregistForm.getEventId());
		List<Category> categoryList = this.eventService.getCategoryList();
		List<Comment> commentList = this.eventService.getCommentList(eventregistForm.getEventId());
		SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
		List<Checked> voteinfoList = this.eventService.getVoteInfoList(eventregistForm.getEventId(),user);
		
		mav.addObject("empInfo",user);
		mav.setViewName("eventdetails");
		mav.addObject("categoryList", categoryList);
		mav.addObject("eventListToId", eventListToId);
		mav.addObject("voteinfoList", voteinfoList);
		mav.addObject("commentList", commentList);
		mav.addObject("commentModel", new CommentForm());
		mav.addObject("voteModel",new VoteForm());
		mav.addObject("eventModel",new EventRegistForm());
		mav.addObject("dbregist","success");
		return mav;
	}

	/**
	 * イベント詳細（イベント削除後）
	 *
	 * @param eventregistForm
	 * @param result
	 * @param mav
	 * @return mav
	 */
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
		mav.addObject("formModel", new SearchForm());
		mav.addObject("dbregist","success");

		return mav;
	}

	/**
	 * イベント登録表示
	 *
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/eventregist",method=RequestMethod.GET)
	public ModelAndView eventregist(ModelAndView mav){
		List<Category> categoryList = this.eventService.getCategoryList();
		SignupForm user = (SignupForm)session.getAttribute("EmpInfo");
		mav.addObject("empInfo",user);
		mav.addObject("categoryList", categoryList);
		mav.addObject("formModel", new EventRegistForm());
		mav.setViewName("eventregist");
		return mav;
	}
}
