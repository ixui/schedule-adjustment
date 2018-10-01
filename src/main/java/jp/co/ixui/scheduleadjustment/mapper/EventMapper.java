package jp.co.ixui.scheduleadjustment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.Event;
import jp.co.ixui.scheduleadjustment.domain.Search;
import jp.co.ixui.scheduleadjustment.domain.VoteInfo;


@Mapper
public interface EventMapper {


	List<Event> selectEventListNotDevision();
	List<Event> selectEventListDevision();
	List<Event> selectEventListEnd();
	List<Event> selectEventListToId(int id);
	List<Event> selectEventListNotDevision(Search searchForm);
	List<Event> selectEventListDevision(Search searchForm);
	List<Event> selectEventListEnd(Search searchForm);
	void createEvent(Event event);
	void decidedDay(VoteInfo voteinfo);
	void eventUpdate(Event event);
	void voteDelete(Event event);
	void candidateDelete(Event event);
	void commentDelete(Event event);
	void eventDelete(Event event);
	Event getEventRegistId();

}

