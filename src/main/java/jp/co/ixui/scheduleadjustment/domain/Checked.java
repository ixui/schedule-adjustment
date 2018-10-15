package jp.co.ixui.scheduleadjustment.domain;

import java.sql.Date;

public class Checked {

	private Date candidateDay;
	private String voteEmpName;
	private boolean check;
	
	public Checked(Date candidateDay,String voteEmpName,boolean check){
		this.candidateDay = candidateDay;
		this.voteEmpName = voteEmpName;
		this.check = check;
	}
	
	public Date getCandidateDay() {
		return candidateDay;
	}
	public void setCandidateDay(Date candidateDay) {
		this.candidateDay = candidateDay;
	}
	
	public String getVoteEmpName() {
		return voteEmpName;
	}
	public void setVoteEmpName(String voteEmpName) {
		this.voteEmpName = voteEmpName;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
}
