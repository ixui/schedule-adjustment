package jp.co.ixui.scheduleadjustment.service;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		if (searchForm.getStartDay()  !="" || searchForm.getEndDay()  !="" || searchForm.getSort()  !="") {
			search.setDecisionDay("開催日：");;
		}
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
		VoteInfo voteInfo = new VoteInfo();
		voteInfo.setEventId(5);

	}

	//コメントを登録する
	public void commentRegist(CommentForm commentForm){
		Comment comment = new Comment();
		comment.setEventId(commentForm.getEventId());
		comment.setEmpNum("459");
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
		VoteInfo voteinfo = new VoteInfo();
		voteinfo.setCandidateDay(java.sql.Date.valueOf(voteForm.getVoteDay()));
		voteinfo.setEventId(voteForm.getEventId());
		voteinfo.setVoteEmpNum("027");
		this.voteMapper.voteDay(voteinfo);
	}


	//イベント情報を登録する
	public void eventUpdate(EventRegistForm eventregistForm){
		Event event = new Event();
		BeanUtils.copyProperties(eventregistForm,event);
		this.eventMapper.eventUpdate(event);
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

