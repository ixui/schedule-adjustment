package com.echigoya.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.echigoya.springboot.domain.CommentList;

@Mapper
public interface CommentMapper {

	List<CommentList> selectComment(int id);
}
