package jp.co.ixui.scheduleadjustment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ixui.scheduleadjustment.domain.Emp;



@Mapper
public interface EmployeeMapper {

	List<Emp> selectEmpName();
	void  createEmp (Emp emp);
	Emp selectUser(String empNum);
}
