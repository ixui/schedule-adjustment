package jp.co.ixui.scheduleadjustment;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import jp.co.ixui.scheduleadjustment.domain.Emp;




public class LoginUserDetails extends User {

	private final Emp emp;

	public String empName;
	public String empNum;
	
	public LoginUserDetails(Emp emp) {
		super(
				emp.getEmpNum(),
				emp.getPassWord(),
				AuthorityUtils.createAuthorityList("ROLE_USER")
				);
		empName = emp.getEmpName();
		empNum = emp.getEmpNum();
		this.emp = emp;
	}

	public Emp getUser() {
		return this.emp;
	}
	
	

}