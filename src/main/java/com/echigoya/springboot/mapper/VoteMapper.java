package com.echigoya.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.echigoya.springboot.domain.VoteInfoList;

@Mapper
public interface VoteMapper {

	List<VoteInfoList> selectCandidateDay(int id);
}
