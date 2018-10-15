package jp.co.ixui.scheduleadjustment.controller.event;

import jp.co.ixui.scheduleadjustment.controller.event.validator.annotation.CheckDate;
import jp.co.ixui.scheduleadjustment.controller.event.validator.annotation.Period;

@Period(fieldFrom="startDay", fieldTo="endDay")

public class SearchForm {


	private String hostNum;
	private String hostName;
	private String categoryNum;
	private String categoryName;
	
	@CheckDate
	private String startDay;
	
	@CheckDate
	private String endDay;
	private String sort;

	public String getHostNum() {
		return hostNum;
	}
	public void setHostNum(String hostNum) {
		this.hostNum = hostNum;
	}
	public String getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
