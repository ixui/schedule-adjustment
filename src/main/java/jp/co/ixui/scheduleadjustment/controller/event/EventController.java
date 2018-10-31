package jp.co.ixui.scheduleadjustment.controller.event;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ixui.scheduleadjustment.LoginUserDetails;
import jp.co.ixui.scheduleadjustment.domain.Category;
import jp.co.ixui.scheduleadjustment.domain.Checked;
import jp.co.ixui.scheduleadjustment.domain.Comment;
import jp.co.ixui.scheduleadjustment.domain.Emp;
import jp.co.ixui.scheduleadjustment.domain.Event;
import jp.co.ixui.scheduleadjustment.domain.Search;
import jp.co.ixui.scheduleadjustment.domain.VoteInfo;
import jp.co.ixui.scheduleadjustment.service.EventService;



@Controller
public class EventController {

	@Autowired
	EventService eventService;
	@Autowired
	HttpSession session;

	public static Logger log = LoggerFactory.getLogger(EventController.class);

	
	/**
	 * NullPointerExceptionの例外処理
	 * 
	 */
	@ExceptionHandler(NullPointerException.class)
	private String nullPointerExceptionHandler() {
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

	
	/**
	 * イベント一覧表示基本セット
	 *
	 * @param mav
	 * @return mav
	 */
	private ModelAndView eventlistBasicSet(ModelAndView mav){
		List<Event> eventListNotDevision = this.eventService.getEventListNotDevision();
		List<Event> eventListDevision = this.eventService.getEventListDevision();
		List<Event> eventListEnd = this.eventService.getEventListEnd();
		List<Category> categoryList = this.eventService.getCategoryList();
		List<Emp> empList = this.eventService.getEmpList();
		
		mav.setViewName("eventlist");
		mav.addObject("eventListNotDevision", eventListNotDevision);
		mav.addObject("eventListDevision", eventListDevision);
		mav.addObject("eventListEnd", eventListEnd);
		mav.addObject("categoryList", categoryList);
		mav.addObject("empList", empList);
		return mav;
	}
	
	
	/**
	 * イベント一覧表示
	 *
	 * @param mav
	 * @return mav
	 */
	@RequestMapping(value="/eventlist" ,method = RequestMethod.GET)
	public ModelAndView eventlist(@AuthenticationPrincipal LoginUserDetails loginUserDetails,ModelAndView mav){
		session.setAttribute("EmpInfo",new Emp(loginUserDetails.getEmpName(),loginUserDetails.getEmpNum()));
		this.eventlistBasicSet(mav);
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
			Emp user = (Emp)session.getAttribute("EmpInfo");
			List<Category> categoryList = this.eventService.getCategoryList();
			mav.addObject("empInfo",user);
			mav.addObject("categoryList", categoryList);
			mav.setViewName("eventregist");
			return mav;
		}
		
		this.eventService.createEvent(eventregistForm);
		this.eventlistBasicSet(mav);
		mav.addObject("formModel", new SearchForm());
		mav.addObject("dbregist","success");
		
		return mav;
	}
	

	/**
	 * イベント一覧表示（絞込み検索の結果）
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
			this.eventlistBasicSet(mav);
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
	 * イベント詳細示基本セット
	 *
	 * @param mav
	 * @return mav
	 */
	private ModelAndView eventdetalisBasicSet(ModelAndView mav,int id){
		Emp user = (Emp)session.getAttribute("EmpInfo");
		List<VoteInfo> voteInfo =this.eventService.getVoteInfo(id);
		boolean delete = this.eventService.canDelete(voteInfo);
		Event eventFromId = this.eventService.getEventFromId(id);
		List<Category> categoryList = this.eventService.getCategoryList();
		List<Checked> voteinfoList = this.eventService.getVoteInfoList(voteInfo,user.getEmpNum());
		List<Comment> commentList = this.eventService.getCommentList(id);

		mav.setViewName("eventdetails");
		mav.addObject("empInfo",user);
		mav.addObject("canDelete",delete);
		mav.addObject("categoryList", categoryList);
		mav.addObject("eventFromId", eventFromId);
		mav.addObject("voteinfoList", voteinfoList);
		mav.addObject("commentList", commentList);

		return  mav;
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
		this.eventdetalisBasicSet(mav,id);
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
			this.eventdetalisBasicSet(mav,commentForm.getEventId());
			mav.addObject("voteModel",new VoteForm());
			mav.addObject("eventModel",new EventRegistForm());
			return mav;
			}
			Emp user = (Emp)session.getAttribute("EmpInfo");
			this.eventService.commentRegist(commentForm,user.getEmpNum());
			Event eventFromId = this.eventService.getEventFromId(commentForm.getEventId());
			List<VoteInfo> voteInfo =this.eventService.getVoteInfo(commentForm.getEventId());
			boolean delete = this.eventService.canDelete(voteInfo);
			List<Category> categoryList = this.eventService.getCategoryList();
			List<Checked> voteinfoList = this.eventService.getVoteInfoList(voteInfo,user.getEmpNum());
			List<Comment> commentList = this.eventService.getCommentList(commentForm.getEventId());
			
			mav.setViewName("eventdetails");
			mav.addObject("empInfo",user);
			mav.addObject("canDelete",delete);
			mav.addObject("categoryList", categoryList);
			mav.addObject("eventFromId", eventFromId);
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
			this.eventdetalisBasicSet(mav,voteForm.getEventId());
			mav.addObject("commentModel", new CommentForm());
			mav.addObject("eventModel",new EventRegistForm());
			return mav;
		}
		this.eventService.decidedDay(voteForm);
		this.eventdetalisBasicSet(mav,voteForm.getEventId());
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
		Emp user = (Emp)session.getAttribute("EmpInfo");
		this.eventService.voteDay(voteForm,user.getEmpNum());
		List<Category> categoryList = this.eventService.getCategoryList();
		List<VoteInfo> voteInfo =this.eventService.getVoteInfo(voteForm.getEventId());
		boolean delete = this.eventService.canDelete(voteInfo);
		Event eventFromId = this.eventService.getEventFromId(voteForm.getEventId());
		List<Checked> voteinfoList = this.eventService.getVoteInfoList(voteInfo,user.getEmpNum());
		List<Comment> commentList = this.eventService.getCommentList(voteForm.getEventId());
		
		mav.setViewName("eventdetails");
		mav.addObject("empInfo",user);
		mav.addObject("canDelete",delete);
		mav.addObject("categoryList", categoryList);
		mav.addObject("eventFromId", eventFromId);
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
			this.eventdetalisBasicSet(mav,eventregistForm.getEventId());
			mav.addObject("commentModel", new CommentForm());
			mav.addObject("voteModel",new VoteForm());
			return mav;
		}
		
		
		List<VoteInfo> voteInfobefore =this.eventService.getVoteInfo(eventregistForm.getEventId());
		boolean delete = this.eventService.canDelete(voteInfobefore);
		this.eventService.eventUpdate(eventregistForm,delete);
		Emp user = (Emp)session.getAttribute("EmpInfo");
		List<Category> categoryList = this.eventService.getCategoryList();
		List<VoteInfo> voteInfo =this.eventService.getVoteInfo(eventregistForm.getEventId());
		Event eventFromId = this.eventService.getEventFromId(eventregistForm.getEventId());
		List<Checked> voteinfoList = this.eventService.getVoteInfoList(voteInfo,user.getEmpNum());
		List<Comment> commentList = this.eventService.getCommentList(eventregistForm.getEventId());
		
		mav.setViewName("eventdetails");
		mav.addObject("empInfo",user);
		mav.addObject("canDelete",delete);
		mav.addObject("categoryList", categoryList);
		mav.addObject("eventFromId", eventFromId);
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
		this.eventService.eventDelete(eventregistForm.getEventId());
		this.eventlistBasicSet(mav);
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
		Emp user = (Emp)session.getAttribute("EmpInfo");
		mav.addObject("empInfo",user);
		mav.addObject("categoryList", categoryList);
		mav.addObject("formModel", new EventRegistForm());
		mav.setViewName("eventregist");
		return mav;
	}
}
