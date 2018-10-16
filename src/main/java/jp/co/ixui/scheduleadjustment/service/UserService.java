package jp.co.ixui.scheduleadjustment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		emp.setEmpNum(signupForm.getEmpNum());
		emp.setEmpName(signupForm.getEmpName());
		emp.setMailAddress(signupForm.getMailAddress());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(signupForm.getPassWord());
		emp.setPassWord(encodedPassword);
		this.employeeMapper.createEmp(emp);

	}
	

}
