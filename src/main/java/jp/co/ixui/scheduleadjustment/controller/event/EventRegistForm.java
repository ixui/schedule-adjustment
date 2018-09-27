package jp.co.ixui.scheduleadjustment.controller.event;

import javax.validation.constraints.NotEmpty;

public class EventRegistForm {

	@NotEmpty(message="イベント名を入力してください")
	private String eventName;
	@NotEmpty(message="カテゴリを選択してください")
	private String categoryId;
	@NotEmpty(message="開催場所を入力してください")
	private String place;
	@NotEmpty(message="コメントを入力してください")
	private String hostComment;
	@NotEmpty(message="開催日を入力してください")
	private String  startDay;
	@NotEmpty(message="開催日を入力してください")
	private String endDay;
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

	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
