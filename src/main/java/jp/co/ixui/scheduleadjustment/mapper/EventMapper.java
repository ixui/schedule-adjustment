package jp.co.ixui.scheduleadjustment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.Event;
import jp.co.ixui.scheduleadjustment.domain.SearchForm;


@Mapper
public interface EventMapper {


	List<Event> selectEventListNotDevision();
	List<Event> selectEventListDevision();
	List<Event> selectEventListEnd();
	List<Event> selectEventListToId(int id);
	List<Event> selectEventListNotDevision(SearchForm searchForm);
	List<Event> selectEventListDevision(SearchForm searchForm);
	List<Event> selectEventListEnd(SearchForm searchForm);
}
