package jp.co.ixui.scheduleadjustment.controller.event;

import javax.validation.constraints.NotEmpty;

public class CommentForm {

	@NotEmpty(message="コメントを入力してください")
	private String participantComment;
	private int eventId;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getParticipantComment() {
		return participantComment;
	}

	public void setParticipantComment(String participantComment) {
		this.participantComment = participantComment;
	}
}
