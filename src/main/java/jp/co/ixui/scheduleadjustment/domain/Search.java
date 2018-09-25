package jp.co.ixui.scheduleadjustment.domain;

import java.sql.Date;

public class Search {

	private String hostNum;
	private String categoryNum;
	private String hostName;
	private String categoryName;
	private Date startDay;
	private Date endDay;
	private String sort;
	private String sortJp;
	private String decisionDay;
	private String between;
	private String until;


	public String getUntil() {
		return until;
	}
	public void setUntil(String until) {
		this.until = until;
	}
	public String getDecisionDay() {
		return decisionDay;
	}
	public void setDecisionDay(String decisionDay) {
		this.decisionDay = decisionDay;
	}
	public String getBetween() {
		return between;
	}
	public void setBetween(String between) {
		this.between = between;
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
	public String getSortJp() {
		return sortJp;
	}
	public void setSortJp(String sortJp) {
		this.sortJp = sortJp;
	}
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}
