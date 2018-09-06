package jp.co.ixui.scheduleadjustment.domain;

import java.util.Date;

public class VoteInfo {

	private int event_id;
	private Date candidate_day;
	private String vote_emp_num;
	private String emp_name;


	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public Date getCandidate_day() {
		return candidate_day;
	}
	public void setCandidate_day(Date candidate_day) {
		this.candidate_day = candidate_day;
	}
	public String getVote_emp_num() {
		return vote_emp_num;
	}
	public void setVote_emp_num(String vote_emp_num) {
		this.vote_emp_num = vote_emp_num;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

}
