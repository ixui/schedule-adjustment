package com.echigoya.springboot.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignupForm {
	@NotEmpty(message="社員番号を入力してください")
	private String empNo;

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailaddress() {
		return mailaddress;
	}

	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	@NotEmpty(message="名前を入力してください")
	private String name;

	@NotEmpty(message="メールアドレスを入力してください")
	private String mailaddress;

	@NotEmpty(message="パスワードを入力してください")
	@Size(min = 8, message="パスワードは8文字以上で入力してください")
	private String pass;

	@NotEmpty(message="確認用パスワードを入力してください")
	private String confirmPass;

}
