package com.echigoya.springboot.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmployeeMaster {

	/**
	 * 社員番号
	 */
	@NotEmpty(message="社員番号を入力してください")
	private String empNo;

	/**
	 * パスワード
	 */
	@NotEmpty(message="パスワードを入力してください")
	private String pass;

	/**
	 * 社員名
	 */
	@NotNull
	private String name;

	/**
	 * メールアドレス
	 */
	@NotNull
	private String mail;

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
