package com.echigoya.springboot.domain;

public class EmpList {
	private String emp_num;
	private String emp_name;
	private String mail_address;
	private String password;
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
