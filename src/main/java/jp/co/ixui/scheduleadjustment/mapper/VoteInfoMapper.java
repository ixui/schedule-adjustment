package jp.co.ixui.scheduleadjustment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.VoteInfo;



@Mapper
public interface VoteInfoMapper {


	void voteDay(List<VoteInfo> voteInfo);
	List<VoteInfo> getParticipant(int id);
	void voteDelete(Map<String, Object> pastVote);


}
