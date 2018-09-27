package jp.co.ixui.scheduleadjustment.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ixui.scheduleadjustment.controller.event.CommentForm;
import jp.co.ixui.scheduleadjustment.controller.event.EventRegistForm;
import jp.co.ixui.scheduleadjustment.controller.event.SearchForm;
import jp.co.ixui.scheduleadjustment.controller.event.VoteForm;
import jp.co.ixui.scheduleadjustment.domain.Category;
import jp.co.ixui.scheduleadjustment.domain.Comment;
import jp.co.ixui.scheduleadjustment.domain.Emp;
import jp.co.ixui.scheduleadjustment.domain.Event;
import jp.co.ixui.scheduleadjustment.domain.Search;
import jp.co.ixui.scheduleadjustment.domain.VoteInfo;
import jp.co.ixui.scheduleadjustment.mapper.CandidateDayMapper;
import jp.co.ixui.scheduleadjustment.mapper.CategoryMapper;
import jp.co.ixui.scheduleadjustment.mapper.CommentMapper;
import jp.co.ixui.scheduleadjustment.mapper.EmployeeMapper;
import jp.co.ixui.scheduleadjustment.mapper.EventMapper;
import jp.co.ixui.scheduleadjustment.mapper.VoteInfoMapper;



@Service
public class EventService {

	@Autowired
	EventMapper eventMapper;
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	VoteInfoMapper voteMapper;
	@Autowired
	CommentMapper commentMapper;
	@Autowired
	CandidateDayMapper candidateDayMapper;

	public static Logger log = LoggerFactory.getLogger(EventService.class);

	//未決定のイベント一覧を取得する（引数なし）
	public List<Event> getEventListNotDevision() {
		List<Event> eventListnd = this.eventMapper.selectEventListNotDevision();
		return eventListnd;
	}

	//決定済みのイベント一覧を取得する（引数なし）
	public List<Event> getEventListDevision() {
		List<Event> eventListd = this.eventMapper.selectEventListDevision();
		return eventListd;
	}

	//終了したイベント一覧を取得する（引数なし）
	public List<Event> getEventListEnd() {
		List<Event> eventListend = this.eventMapper.selectEventListEnd();
		return eventListend;
	}

	//イベント一覧を取得する（引数：イベントID）
	public List<Event> getEventListeventListToId(int id){
		List<Event> eventListtoid= this.eventMapper.selectEventListToId(id);
		return eventListtoid;
	}

	//カテゴリ一覧を取得する
	public List<Category> getCategoryList(){
		List<Category> categoryList= this.categoryMapper.selectCategoryList();
		return categoryList;
	}

	//社員一覧を取得する
	public List<Emp> getEmpList(){
		List<Emp> empList= this.employeeMapper.selectEmpName();
		return empList;
	}


	//投票情報を取得する
	public Map<Date, String> getVoteInfoList(int id){
		List<VoteInfo> voteList= this.candidateDayMapper.selectCandidateDay(id);
		Map<Date,String> voteResult = new LinkedHashMap<>();
		//日付が同じ場合、投票者を横に並べる
		for(VoteInfo vl :voteList){
			if(voteResult.containsKey(vl.getCandidateDay())){
				voteResult.put(vl.getCandidateDay(),voteResult.get(vl.getCandidateDay()) + " , " + vl.getEmpName());
			}else{voteResult.put(vl.getCandidateDay(), vl.getEmpName());
				}
        }
		return voteResult;
	}


	//コメント一覧を取得する
	public List<Comment> getCommentList(int id){
		List<Comment> commentList= this.commentMapper.selectComment(id);
		return commentList;
	}

	//未決定のイベント一覧を取得する（引数：検索条件）
	public List<Event> getEventListNotDevision(SearchForm searchForm)  {
		Search search = new Search();
        search.setHostNum(searchForm.getHostNum());
        search.setCategoryNum(searchForm.getCategoryNum());
        List<Event> eventListnd = this.eventMapper.selectEventListNotDevision(search);
        return eventListnd;
	}

	//決定済みのイベント一覧を取得する（引数：検索条件）
	public List<Event> getEventListDevision(SearchForm searchForm)  {
		Search search = new Search();
		if (searchForm.getStartDay()  !="") {
			search.setStartDay(java.sql.Date.valueOf(searchForm.getStartDay()));

		}
		if (searchForm.getEndDay()  !="") {
			search.setEndDay(java.sql.Date.valueOf(searchForm.getEndDay()));
		}

        search.setHostNum(searchForm.getHostNum());
        search.setCategoryNum(searchForm.getCategoryNum());
        search.setSort(searchForm.getSort());
        List<Event> eventListd = this.eventMapper.selectEventListDevision(search);
		return eventListd;
	}

	//終了したイベント一覧を取得する（引数：検索条件）
	public List<Event> getEventListEnd(SearchForm searchForm) {
		 Search search = new Search();
		if (searchForm.getStartDay()  !="") {
			search.setStartDay(java.sql.Date.valueOf(searchForm.getStartDay()));
		}
		if (searchForm.getEndDay()  !="") {
			search.setEndDay(java.sql.Date.valueOf(searchForm.getEndDay()));
		}
        search.setHostNum(searchForm.getHostNum());
        search.setCategoryNum(searchForm.getCategoryNum());
        search.setSort(searchForm.getSort());
        List<Event> eventListend = this.eventMapper.selectEventListEnd(search);
		return eventListend;
	}

	//検索条件の表示
	public Search getSearchCondition(SearchForm searchForm){
		Search search = new Search();

		if (searchForm.getSort().equals("asc")) {
			search.setSortJp("昇順");
		} else if (searchForm.getSort().equals("desc")) {
			search.setSortJp("降順");
		}
		if (searchForm.getStartDay()  !="") {
			search.setStartDay(java.sql.Date.valueOf(searchForm.getStartDay()) );
		}
		if (searchForm.getEndDay()  !="") {
			search.setEndDay(java.sql.Date.valueOf(searchForm.getEndDay()));
		}
		if (searchForm.getHostName()  !="") {
			 search.setHostName("主催者：" + searchForm.getHostName());
		}
		if (searchForm.getCategoryName()  !="") {
			search.setCategoryName("カテゴリ：" + searchForm.getCategoryName());
		}
			search.setDecisionDay("開催日：");
		if (searchForm.getStartDay()  !="" ) {
				search.setBetween("から");
		}
		if (searchForm.getEndDay()  !="" ) {
			search.setUntil("まで");
		}

		return search;
	}

	//イベントを登録する
	public void createEvent(EventRegistForm eventregistForm) {
		Event event = new Event();
		event.setHostNum("459");
		event.setEventName(eventregistForm.getEventName());
		event.setCategoryId(eventregistForm.getCategoryId());
		event.setPlace(eventregistForm.getPlace());
		event.setHostComment(eventregistForm.getHostComment());
		this.eventMapper.createEvent(event);
	}

	//イベントの候補日を登録する
	public void candidateDay(EventRegistForm eventregistForm) {
		List<VoteInfo> voteInfo = new ArrayList<VoteInfo>();
		Calendar startDayCal = Calendar.getInstance();
		startDayCal.setTime(java.sql.Date.valueOf(eventregistForm.getStartDay()) );
		Calendar endDayCal = Calendar.getInstance();
		endDayCal.setTime(java.sql.Date.valueOf(eventregistForm.getEndDay()) );
		voteInfo.add(new VoteInfo (30,(java.sql.Date.valueOf(eventregistForm.getStartDay()) )));
		boolean roop = true;
		while (roop){
			startDayCal.add(Calendar.DAY_OF_MONTH, 1);
			java.sql.Date candidateday = new java.sql.Date(startDayCal.getTimeInMillis());
			voteInfo.add(new VoteInfo (30,candidateday));
			if (startDayCal.equals(endDayCal)){
				roop =false;
			}
		}
		Iterator<VoteInfo> it = voteInfo.iterator();
        while (it.hasNext()) {
        	VoteInfo data = it.next();
        	log.debug(data.getEventId() + ":" + data.getCandidateDay());
        }
        this.candidateDayMapper.candidateDay(voteInfo);
	}

	//コメントを登録する
	public void commentRegist(CommentForm commentForm){
		Comment comment = new Comment();
		comment.setEventId(commentForm.getEventId());
		comment.setEmpNum("4336");
		comment.setParticipantComment(commentForm.getParticipantComment());
		this.commentMapper.commentRegist(comment);
	}

	//開催日を登録する
	public void decidedDay(VoteForm voteForm){
		VoteInfo voteinfo = new VoteInfo();
		voteinfo.setCandidateDay(java.sql.Date.valueOf(voteForm.getVoteDay()));
		voteinfo.setEventId(voteForm.getEventId());
		this.eventMapper.decidedDay(voteinfo);
	}

	//投票する
	public void voteDay(VoteForm voteForm){
		this.voteMapper.voteDelete(voteForm.getEventId());
		if (voteForm.getVoteDay()  !=null) {
			List<VoteInfo> voteInfo = new ArrayList<VoteInfo>();
			String[] voteDay = voteForm.getVoteDay().split(",", 0);
			for (int i = 0; i<voteDay.length; i++){
				voteInfo.add(new VoteInfo (voteForm.getEventId(),(java.sql.Date.valueOf(voteDay[i]) ),"294"));
			}
			Iterator<VoteInfo> it = voteInfo.iterator();
	        while (it.hasNext()) {
	        	VoteInfo data = it.next();
	        	log.debug(data.getEventId() + ":" + data.getCandidateDay() + ":" + data.getVoteEmpNum());
	        }
			this.voteMapper.voteDay(voteInfo);
		}
	}


	//イベント情報を変更する
	public void eventUpdate(EventRegistForm eventregistForm){
		Event event = new Event();
		BeanUtils.copyProperties(eventregistForm,event);
		this.eventMapper.eventUpdate(event);
	}

	//イベントの候補日を登録する
		public void candidateUpdate(EventRegistForm eventregistForm) {
			 this.candidateDayMapper.candidateDelete(eventregistForm.getEventId());
			List<VoteInfo> voteInfo = new ArrayList<VoteInfo>();
			Calendar startDayCal = Calendar.getInstance();
			startDayCal.setTime(java.sql.Date.valueOf(eventregistForm.getStartDay()) );
			Calendar endDayCal = Calendar.getInstance();
			endDayCal.setTime(java.sql.Date.valueOf(eventregistForm.getEndDay()) );
			voteInfo.add(new VoteInfo (eventregistForm.getEventId(),(java.sql.Date.valueOf(eventregistForm.getStartDay()) )));
			boolean roop = true;
			while (roop){
				startDayCal.add(Calendar.DAY_OF_MONTH, 1);
				java.sql.Date candidateday = new java.sql.Date(startDayCal.getTimeInMillis());
				voteInfo.add(new VoteInfo (eventregistForm.getEventId(),candidateday));
				if (startDayCal.equals(endDayCal)){
					roop =false;
				}
			}
			Iterator<VoteInfo> it = voteInfo.iterator();
	        while (it.hasNext()) {
	        	VoteInfo data = it.next();
	        	log.debug(data.getEventId() + ":" + data.getCandidateDay());
	        }
	        this.candidateDayMapper.candidateDay(voteInfo);
		}

	//イベントを削除する
	public void eventDelete(EventRegistForm eventregistForm){
		Event event = new Event();
		event.setEventId(eventregistForm.getEventId());
		this.eventMapper.voteDelete(event);
		this.eventMapper.candidateDelete(event);
		this.eventMapper.commentDelete(event);
		this.eventMapper.eventDelete(event);
	}

	//詳細画面再表示
	public void detailsRedisplay(EventRegistForm eventForm){
		Event event = new Event();
		event.setEventId(eventForm.getEventId());
	}
}

