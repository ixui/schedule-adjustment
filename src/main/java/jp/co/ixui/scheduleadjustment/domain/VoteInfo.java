package jp.co.ixui.scheduleadjustment.domain;

import java.sql.Date;

public class VoteInfo {

	private int eventId;
	private Date candidateDay;
	private String voteEmpNum;
	private String empName;

	public VoteInfo(){

	}

	public VoteInfo(int eventId, Date candidateDay) {
		this.eventId = eventId;
		this.candidateDay = candidateDay;
    }

	public VoteInfo(int eventId, Date candidateDay,String voteEmpNum) {
		this.eventId = eventId;
		this.candidateDay = candidateDay;
		this.voteEmpNum = voteEmpNum;
    }

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
