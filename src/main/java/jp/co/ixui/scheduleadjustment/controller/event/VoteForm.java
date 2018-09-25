package jp.co.ixui.scheduleadjustment.controller.event;

public class VoteForm {

	private String voteDay;
	private String participantId;
	private int eventId;



	public String getVoteDay() {
		return voteDay;
	}
	public void setVoteDay(String voteDay) {
		this.voteDay = voteDay;
	}
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
}
