package com.echigoya.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.echigoya.springboot.domain.EmpList;

@Mapper
public interface EmployeeMapper {

	List<EmpList> selectEmpName();
}
