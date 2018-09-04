package com.echigoya.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echigoya.springboot.domain.CategoryList;
import com.echigoya.springboot.domain.CommentList;
import com.echigoya.springboot.domain.EmpList;
import com.echigoya.springboot.domain.EventList;
import com.echigoya.springboot.domain.VoteInfoList;
import com.echigoya.springboot.mapper.CategoryMapper;
import com.echigoya.springboot.mapper.CommentMapper;
import com.echigoya.springboot.mapper.EmployeeMapper;
import com.echigoya.springboot.mapper.EventMapper;
import com.echigoya.springboot.mapper.VoteMapper;

@Service
public class HoppyService {

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


	/*@Transactional
	public void createUser(SignupForm signupDTO) {
		EmployeeMaster employee = new EmployeeMaster();
		employee.setEmpNo(signupDTO.getEmpNo());
		employee.setName(signupDTO.getName());
		employee.setMail(signupDTO.getMailaddress());
		employee.setPass(signupDTO.getPass());
		this.eventMapper.create(employee);
	}
	*/

	public List<EventList> getEventListNotDevision() {
		List<EventList> eventListnd = this.eventMapper.selectEventListNotDevision();
		return eventListnd;
	}

	public List<EventList> getEventListDevision() {
		List<EventList> eventListd = this.eventMapper.selectEventListDevision();
		return eventListd;
	}

	public List<EventList> getEventListEnd() {
		List<EventList> eventListend = this.eventMapper.selectEventListEnd();
		return eventListend;
	}

	public List<EventList> getEventListeventListToId(int id){
		List<EventList> eventListtoid= this.eventMapper.selectEventListToId(id);
		return eventListtoid;
	}



	public List<CategoryList> getCategoryList(){
		List<CategoryList> categoryList= this.categoryMapper.selectCategoryList();
		return categoryList;
	}

	public List<EmpList> getEmpList(){
		List<EmpList> empList= this.employeeMapper.selectEmpName();
		return empList;
	}

	public List<VoteInfoList> getVoteInfoList(int id){
		List<VoteInfoList> voteList= this.voteMapper.selectCandidateDay(id);
		return voteList;
	}

	public List<CommentList> getCommentList(int id){
		List<CommentList> commentList= this.commentMapper.selectComment(id);
		return commentList;
	}
}