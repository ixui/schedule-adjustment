package jp.co.ixui.scheduleadjustment.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.VoteInfo;



@Mapper
public interface VoteInfoMapper {


	void voteDay(VoteInfo voteinfo);

}
