package jp.co.ixui.scheduleadjustment.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ixui.scheduleadjustment.controller.event.CommentForm;
import jp.co.ixui.scheduleadjustment.controller.event.EventRegistForm;
import jp.co.ixui.scheduleadjustment.controller.event.SearchForm;
import jp.co.ixui.scheduleadjustment.controller.event.VoteForm;
import jp.co.ixui.scheduleadjustment.domain.Category;
import jp.co.ixui.scheduleadjustment.domain.Checked;
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


@Transactional
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

	/**
	 * 未決定のイベント一覧を取得する（引数なし）
	 *
	 * @return eventListnd
	 */
	public List<Event> getEventListNotDevision() {
		List<Event> eventListnd = this.eventMapper.selectEventListNotDevision();
		return eventListnd;
	}

	/**
	 * 決定済みのイベント一覧を取得する（引数なし）
	 *
	 * @return eventListd
	 */
	public List<Event> getEventListDevision() {
		List<Event> eventListd = this.eventMapper.selectEventListDevision();
		return eventListd;
	}

	/**
	 * 終了したイベント一覧を取得する（引数なし）
	 *
	 * @return eventListend
	 */
	public List<Event> getEventListEnd() {
		List<Event> eventListend = this.eventMapper.selectEventListEnd();
		return eventListend;
	}

	/**
	 * イベント詳細を取得する
	 *
	 * @param id イベントID
	 * @return eventListtoid
	 */
	public List<Event> getEventListeventListToId(int id){
		List<Event> eventListtoid= this.eventMapper.selectEventListToId(id);
		return eventListtoid;
	}

	/**
	 * カテゴリ一覧を取得する
	 *
	 * @return categoryList
	 */
	public List<Category> getCategoryList(){
		List<Category> categoryList= this.categoryMapper.selectCategoryList();
		return categoryList;
	}

	/**
	 * 社員一覧を取得する
	 *
	 * @return empList
	 */
	public List<Emp> getEmpList(){
		List<Emp> empList= this.employeeMapper.selectEmpName();
		return empList;
	}
	

	/**
	 * 投票情報を取得する
	 *
	 * @param id イベントid
	 * @return voteList
	 */
	public List<VoteInfo> getVoteInfo(int id){
		List<VoteInfo> voteList= this.candidateDayMapper.selectCandidateDay(id);
		return voteList;
		
	}
	
	public boolean canDelete(List<VoteInfo> voteList){
		boolean delete = true;
		for (VoteInfo vl :voteList) {
			if (vl.getEmpName() != null){
				delete = false;
				break;
			}
		}
		return delete;
	}
	
	
	
	/**
	 * 投票情報を取得して表示用のリストを作る
	 *
	 * @param voteList 投票情報
	 * @return voteResult
	 */
	public List<Checked> getVoteInfoList(List<VoteInfo> voteList,Emp user){
		List<Checked> voteResult = new ArrayList<>();
		
		for (VoteInfo vl :voteList) {
			boolean isChecked = false;
			//ログインしている人と同じ社員番号があったら、チェックを入れる
			if (Objects.equals(vl.getVoteEmpNum(), user.getEmpNum())) {
				isChecked = true;
			}
			//日付が同じ場合、投票者を横に並べる
			if (!voteResult.isEmpty() && vl.getCandidateDay()
					.equals(voteResult.get(voteResult.size() - 1).getCandidateDay())) {
				if (voteResult.get(voteResult.size() - 1).isCheck()) {
					isChecked = true;
				}
				voteResult.set(voteResult.size() - 1,
						new Checked(vl.getCandidateDay(),voteResult.get(voteResult.size() - 1)
								.getVoteEmpName() +" , "+ vl.getEmpName(),isChecked));
			}else{
				voteResult.add(new Checked(vl.getCandidateDay(), vl.getEmpName(),isChecked));
				}
		}
		
		return voteResult;
	}
	

	/**
	 * コメント一覧を取得する
	 *
	 * @param id イベントID
	 * @return commentList
	 */
	public List<Comment> getCommentList(int id){
		List<Comment> commentList= this.commentMapper.selectComment(id);
		return commentList;
	}
	
	/**
	 * 未決定のイベント一覧を取得する
	 *
	 * @param searchForm 検索条件
	 * @return eventListnd
	 */
	public List<Event> getEventListNotDevision(SearchForm searchForm)  {
		Search search = new Search();
		search.setHostNum(searchForm.getHostNum());
		search.setCategoryNum(searchForm.getCategoryNum());
		List<Event> eventListnd = this.eventMapper.selectEventListNotDevision(search);
		return eventListnd;
	}

	/**
	 * 決定済みのイベント一覧を取得する
	 *
	 * @param searchForm 検索条件
	 * @return eventListd
	 */
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

	/**
	 * 終了したイベント一覧を取得する
	 *
	 * @param searchForm 検索条件
	 * @return eventListend
	 */
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

	/**
	 * 検索条件の表示
	 *
	 * @param searchForm 検索条件
	 * @return search
	 */
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

	/**
	 * イベントを登録する
	 *
	 * @param eventregistForm
	 */
	public void createEvent(EventRegistForm eventregistForm) {
		this.createEventExceptDay(eventregistForm);
		EventRegistForm eventRegistId = this.getEventRegistId(eventregistForm);
		this.candidateDay(eventRegistId);
	}
	
	/**
	 * イベントを登録する（候補日以外）
	 *
	 * @param eventregistForm
	 */
	private void createEventExceptDay(EventRegistForm eventregistForm) {
		Event event = new Event();
		event.setHostNum(eventregistForm.getHostNum());
		event.setEventName(eventregistForm.getEventName());
		event.setCategoryId(eventregistForm.getCategoryId());
		event.setPlace(eventregistForm.getPlace());
		event.setHostComment(eventregistForm.getHostComment());
		this.eventMapper.createEvent(event);
	}

	/**
	 * 登録したイベントのIDのイベント情報を取得する
	 *
	 * @param eventregistForm
	 * @return eventregistForm
	 */
	private EventRegistForm getEventRegistId(EventRegistForm eventregistForm) {
		Event event = this.eventMapper.getEventRegistId();
		eventregistForm.setEventId(event.getEventId());
		return eventregistForm;
	}

	/**
	 * イベントの候補日を登録する
	 *
	 * @param eventRegistForm 
	 */
	private void candidateDay(EventRegistForm eventRegistForm) {
		List<VoteInfo> voteInfo = new ArrayList<VoteInfo>();
		Calendar startDayCal = Calendar.getInstance();
		startDayCal.setTime(java.sql.Date.valueOf(eventRegistForm.getStartDay()) );
		Calendar endDayCal = Calendar.getInstance();
		endDayCal.setTime(java.sql.Date.valueOf(eventRegistForm.getEndDay()) );
		voteInfo.add(new VoteInfo(eventRegistForm.getEventId(), (java.sql.Date.valueOf(eventRegistForm.getStartDay()))));
		while (true){
			if (startDayCal.equals(endDayCal)){
				break;
			}
			startDayCal.add(Calendar.DAY_OF_MONTH, 1);
			java.sql.Date candidateday = new java.sql.Date(startDayCal.getTimeInMillis());
			voteInfo.add(new VoteInfo(eventRegistForm.getEventId(), candidateday));
			
		}
		Iterator<VoteInfo> it = voteInfo.iterator();
		while (it.hasNext()) {
			VoteInfo data = it.next();
			log.debug(data.getEventId() + ":" + data.getCandidateDay());
		}
		this.candidateDayMapper.candidateDay(voteInfo);
	}

	/**
	 * コメントを登録する
	 *
	 * @param commentForm
	 * @param user 
	 */
	public void commentRegist(CommentForm commentForm, Emp user){
		Comment comment = new Comment();
		comment.setEventId(commentForm.getEventId());
		comment.setEmpNum(user.getEmpNum());
		comment.setParticipantComment(commentForm.getParticipantComment());
		this.commentMapper.commentRegist(comment);
	}

	/**
	 * 開催日を登録する
	 *
	 * @param commentForm
	 */
	public void decidedDay(VoteForm voteForm){
		VoteInfo voteinfo = new VoteInfo();
		voteinfo.setCandidateDay(java.sql.Date.valueOf(voteForm.getVoteDay()));
		voteinfo.setEventId(voteForm.getEventId());
		this.eventMapper.decidedDay(voteinfo);
	}

	/**
	 * 投票する
	 *
	 * @param voteForm
	 * @param user 
	 */
	public void voteDay(VoteForm voteForm, Emp user){
		Map<String, Object> pastVote = new LinkedHashMap<>();
		pastVote.put("eventId",voteForm.getEventId());
		pastVote.put("empNum",user.getEmpNum());
		this.voteMapper.voteDelete(pastVote);
		if (voteForm.getVoteDay()  !=null) {
			List<VoteInfo> voteInfo = new ArrayList<VoteInfo>();
			String[] voteDay = voteForm.getVoteDay().split(",", 0);
			for (int i = 0; i<voteDay.length; i++){
				voteInfo.add(new VoteInfo (voteForm.getEventId(),(java.sql.Date.valueOf(voteDay[i]) ),user.getEmpNum()));
			}
			Iterator<VoteInfo> it = voteInfo.iterator();
			while (it.hasNext()) {
				VoteInfo data = it.next();
				log.debug(data.getEventId() + ":" + data.getCandidateDay() + ":" + data.getVoteEmpNum());
			}
			this.voteMapper.voteDay(voteInfo);
		}
	}


	/**
	 * イベント情報を変更する
	 *
	 * @param eventregistForm
	 */
	public void eventUpdate(EventRegistForm eventregistForm){
		Event event = new Event();
		BeanUtils.copyProperties(eventregistForm,event);
		this.eventMapper.eventUpdate(event);
	}

	/**
	 * イベントの候補日を変更する
	 *
	 * @param eventregistForm
	 */
	public void candidateUpdate(EventRegistForm eventregistForm) {
		//投票者情報を取得する
		List<VoteInfo> participant = this.voteMapper.getParticipant(eventregistForm.getEventId());
		//投票がされている場合は処理を行わない
		if(participant.isEmpty()){
			//一度前の候補日を削除する
			this.candidateDayMapper.candidateDelete(eventregistForm.getEventId());
			this.candidateDay(eventregistForm);
		}
	}

	/**
	 * イベントを削除する
	 *
	 * @param eventregistForm
	 */
	public void eventDelete(EventRegistForm eventregistForm){
		int id = eventregistForm.getEventId();
		this.candidateDayMapper.candidateDelete(id);
		this.commentMapper.commentDelete(id);
		this.eventMapper.eventDelete(id);
	}

	
}

