package com.echigoya.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.echigoya.springboot.domain.EventList;

@Mapper
public interface EventMapper {


	List<EventList> selectEventListNotDevision();
	List<EventList> selectEventListDevision();
	List<EventList> selectEventListEnd();
	List<EventList> selectEventListToId(int id);
}
