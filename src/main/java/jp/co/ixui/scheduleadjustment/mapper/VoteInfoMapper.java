package jp.co.ixui.scheduleadjustment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.VoteInfo;



@Mapper
public interface VoteInfoMapper {


	void voteDay(List<VoteInfo> voteInfo);
	void voteDelete(int id);

}
