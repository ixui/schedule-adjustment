package jp.co.ixui.scheduleadjustment;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import jp.co.ixui.scheduleadjustment.domain.Emp;




public class LoginUserDetails extends User {

	private final Emp emp;

	public LoginUserDetails(Emp emp) {
		super(
				emp.getEmpNum(),
				emp.getPassWord(),
				AuthorityUtils.createAuthorityList("ROLE_USER")
				);
		this.emp = emp;
	}

	public Emp getUser() {
		return this.emp;
	}

}