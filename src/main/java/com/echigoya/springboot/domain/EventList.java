package com.echigoya.springboot.domain;

import java.util.Date;

public class EventList {

	private int event_id;
	private String event_name;
	private String host_num;
	private String category_id;
	private String place;
	private String host_comment;
	private Date decision_day;
	private String emp_num;
	private String emp_name;
	private String category_name;


	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getHost_num() {
		return host_num;
	}
	public void setHost_num(String host_num) {
		this.host_num = host_num;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getHost_comment() {
		return host_comment;
	}
	public void setHost_comment(String host_comment) {
		this.host_comment = host_comment;
	}

	public Date getDecision_day() {
		return decision_day;
	}

	public void setDecision_day(Date decision_day) {
		this.decision_day = decision_day;
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
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
}