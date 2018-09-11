package jp.co.ixui.scheduleadjustment.domain;

import java.util.Date;

public class Event {

	private int eventId;
	private String eventName;
	private String hostNum;
	private String categoryId;
	private String place;
	private String hostComment;
	private Date decisionDay;
	private String empNum;
	private String empName;
	private String categoryName;
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getHostNum() {
		return hostNum;
	}
	public void setHostNum(String hostNum) {
		this.hostNum = hostNum;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getHostComment() {
		return hostComment;
	}
	public void setHostComment(String hostComment) {
		this.hostComment = hostComment;
	}
	public Date getDecisionDay() {
		return decisionDay;
	}
	public void setDecisionDay(Date decisionDay) {
		this.decisionDay = decisionDay;
	}
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


}