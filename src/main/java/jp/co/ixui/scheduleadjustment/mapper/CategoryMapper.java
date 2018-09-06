package jp.co.ixui.scheduleadjustment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.Category;



@Mapper
public interface CategoryMapper {

	List<Category> selectCategoryList();
}
