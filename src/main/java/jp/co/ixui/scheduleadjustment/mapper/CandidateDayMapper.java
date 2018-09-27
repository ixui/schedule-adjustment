package jp.co.ixui.scheduleadjustment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.VoteInfo;

@Mapper
public interface CandidateDayMapper {

	 List<VoteInfo> selectCandidateDay(int id) ;
	void candidateDay(List<VoteInfo> voteInfo);
	void candidateDelete(int id);

}
