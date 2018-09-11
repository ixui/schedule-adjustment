package jp.co.ixui.scheduleadjustment.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ixui.scheduleadjustment.domain.Category;
import jp.co.ixui.scheduleadjustment.domain.Comment;
import jp.co.ixui.scheduleadjustment.domain.Emp;
import jp.co.ixui.scheduleadjustment.domain.Event;
import jp.co.ixui.scheduleadjustment.domain.SearchForm;
import jp.co.ixui.scheduleadjustment.domain.VoteInfo;
import jp.co.ixui.scheduleadjustment.mapper.CategoryMapper;
import jp.co.ixui.scheduleadjustment.mapper.CommentMapper;
import jp.co.ixui.scheduleadjustment.mapper.EmployeeMapper;
import jp.co.ixui.scheduleadjustment.mapper.EventMapper;
import jp.co.ixui.scheduleadjustment.mapper.VoteMapper;



@Service
public class EventService {

	@Autowired
	EventMapper eventMapper;
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	VoteMapper voteMapper;
	@Autowired
	CommentMapper commentMapper;


	public List<Event> getEventListNotDevision() {
		List<Event> eventListnd = this.eventMapper.selectEventListNotDevision();
		return eventListnd;
	}

	public List<Event> getEventListDevision() {
		List<Event> eventListd = this.eventMapper.selectEventListDevision();
		return eventListd;
	}

	public List<Event> getEventListEnd() {
		List<Event> eventListend = this.eventMapper.selectEventListEnd();
		return eventListend;
	}

	public List<Event> getEventListeventListToId(int id){
		List<Event> eventListtoid= this.eventMapper.selectEventListToId(id);
		return eventListtoid;
	}

	public List<Category> getCategoryList(){
		List<Category> categoryList= this.categoryMapper.selectCategoryList();
		return categoryList;
	}

	public List<Emp> getEmpList(){
		List<Emp> empList= this.employeeMapper.selectEmpName();
		return empList;
	}


	public Map<Date, String> getVoteInfoList(int id){
		List<VoteInfo> voteList= this.voteMapper.selectCandidateDay(id);
		Map<Date,String> voteResult = new LinkedHashMap<>();
		for(VoteInfo vl :voteList){
			if(voteResult.containsKey(vl.getCandidateDay())){
				voteResult.put(vl.getCandidateDay(),voteResult.get(vl.getCandidateDay()) + " , " + vl.getEmpName());
			}else{voteResult.put(vl.getCandidateDay(), vl.getEmpName());
				}
        }
		System.out.println(voteResult);
		return voteResult;
	}





	public List<Comment> getCommentList(int id){
		List<Comment> commentList= this.commentMapper.selectComment(id);
		return commentList;
	}

	public List<Event> getEventListNotDevision(SearchForm searchForm) {
		List<Event> eventListnd = this.eventMapper.selectEventListNotDevision(searchForm);
		return eventListnd;
	}

	public List<Event> getEventListDevision(SearchForm searchForm) {
		List<Event> eventListd = this.eventMapper.selectEventListDevision(searchForm);
		return eventListd;
	}

	public List<Event> getEventListEnd(SearchForm searchForm) {
		List<Event> eventListend = this.eventMapper.selectEventListEnd(searchForm);
		return eventListend;
	}
}
