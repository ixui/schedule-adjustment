package jp.co.ixui.scheduleadjustment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.VoteInfo;



@Mapper
public interface VoteMapper {

	List<VoteInfo> selectCandidateDay(int id);
}
