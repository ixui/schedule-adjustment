package jp.co.ixui.scheduleadjustment.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ixui.scheduleadjustment.controller.login.SignupForm;
import jp.co.ixui.scheduleadjustment.domain.Emp;
import jp.co.ixui.scheduleadjustment.mapper.EmployeeMapper;

@Service
public class UserService {
	@Autowired
	EmployeeMapper employeeMapper;

	public void createUser(SignupForm signupForm) {
		Emp emp = new Emp();
		BeanUtils.copyProperties(signupForm,emp);
		this.employeeMapper.createEmp(emp);

	}

}
