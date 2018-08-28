package com.echigoya.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.echigoya.springboot.domain.EventList;

@Mapper
public interface hoppyMapper {


	List<EventList> selectEventListNotDevision();
}
