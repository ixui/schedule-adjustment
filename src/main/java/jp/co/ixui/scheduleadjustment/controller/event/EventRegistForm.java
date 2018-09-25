package jp.co.ixui.scheduleadjustment.controller.event;

import java.sql.Date;

public class EventRegistForm {

	private String eventName;
	private String categoryId;
	private String place;
	private String hostComment;
	private Date startDay;
	private Date endDay;
	private int eventId;


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
	public Date getStartDay() {
		return startDay;
	}
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
