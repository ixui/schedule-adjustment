package jp.co.ixui.scheduleadjustment.domain;

public class Comment {

	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public String getParticipantComment() {
		return participantComment;
	}
	public void setParticipantComment(String participantComment) {
		this.participantComment = participantComment;
	}
	private int eventId;
	private int commentId;
	private String empNum;
	private String empName;
	private String participantComment;
}