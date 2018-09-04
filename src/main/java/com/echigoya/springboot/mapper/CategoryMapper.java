package com.echigoya.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.echigoya.springboot.domain.CategoryList;

@Mapper
public interface CategoryMapper {

	List<CategoryList> selectCategoryList();
}
