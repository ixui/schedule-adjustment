package jp.co.ixui.scheduleadjustment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.Comment;


@Mapper
public interface CommentMapper {

	List<Comment> selectComment(int id);
	void commentRegist(Comment comment);
	void commentDelete(int id);
}
