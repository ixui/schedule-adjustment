package jp.co.ixui.scheduleadjustment;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import jp.co.ixui.scheduleadjustment.domain.Emp;




public class LoginUserDetails extends User {

	private final Emp emp;

	private String empName;
	private String empNum;
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public LoginUserDetails(Emp emp) {
		super(
				emp.getEmpNum(),
				emp.getPassWord(),
				AuthorityUtils.createAuthorityList("ROLE_USER")
				);
		setEmpName(emp.getEmpName()) ;
		setEmpNum(emp.getEmpNum());
		this.emp = emp;
	}

	public Emp getUser() {
		return this.emp;
	}
	
	

}