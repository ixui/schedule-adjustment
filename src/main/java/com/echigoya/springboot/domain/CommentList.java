package com.echigoya.springboot.domain;

public class CommentList {

	private int event_id;
	private int comment_id;
	private String emp_num;
	private String emp_name;
	private String participant_comment;


	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getParticipant_comment() {
		return participant_comment;
	}
	public void setParticipant_comment(String participant_comment) {
		this.participant_comment = participant_comment;
	}

}
