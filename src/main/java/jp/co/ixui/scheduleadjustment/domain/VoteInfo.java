package jp.co.ixui.scheduleadjustment.domain;

import java.util.Date;

public class VoteInfo {

	private int eventId;
	private Date candidateDay;
	private String voteEmpNum;
	private String empName;


	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public Date getCandidateDay() {
		return candidateDay;
	}
	public void setCandidateDay(Date candidateDay) {
		this.candidateDay = candidateDay;
	}
	public String getVoteEmpNum() {
		return voteEmpNum;
	}
	public void setVoteEmpNum(String voteEmpNum) {
		this.voteEmpNum = voteEmpNum;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}



}
