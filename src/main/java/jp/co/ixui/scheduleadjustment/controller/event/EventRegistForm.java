package jp.co.ixui.scheduleadjustment.controller.event;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import jp.co.ixui.scheduleadjustment.controller.event.validator.annotation.CheckDate;
import jp.co.ixui.scheduleadjustment.controller.event.validator.annotation.Future;
import jp.co.ixui.scheduleadjustment.controller.event.validator.annotation.Period;

@Period(fieldFrom="startDay", fieldTo="endDay")

public class EventRegistForm {

	@Size(max = 20, message="文字数オーバーです")
	@NotEmpty(message="イベント名を入力してください")
	private String eventName;
	
	
	@NotEmpty(message="カテゴリを選択してください")
	private String categoryId;
	
	@Size(max = 20, message="文字数オーバーです")
	@NotEmpty(message="開催場所を入力してください")
	private String place;
	
	@Size(max = 200, message="文字数オーバーです")
	@NotEmpty(message="コメントを入力してください")
	private String hostComment;
	
	@CheckDate
	@Future
	@NotEmpty(message="開催日の始めを入力してください　　　　")
	private String  startDay;
	
	@CheckDate
	@NotEmpty(message="開催日の終わりを入力してください")
	private String endDay;
	
	
	private int eventId;
	
	
	private String hostNum;


	public String getHostNum() {
		return hostNum;
	}
	public void setHostNum(String hostNum) {
		this.hostNum = hostNum;
	}
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
