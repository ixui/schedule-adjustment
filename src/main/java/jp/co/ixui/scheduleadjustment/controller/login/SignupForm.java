package jp.co.ixui.scheduleadjustment.controller.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SignupForm {

	@NotEmpty(message="社員番号を入力してください")
	private String empNum;
	@NotEmpty(message="お名前を入力してください")
	private String empName;
	@NotEmpty(message="メールアドレスを入力してください")
	@Email(message="メールアドレスの形式にしてください")
	private String mailAddress;
	@NotEmpty(message="パスワードを入力してください")
	private String passWord;
	@NotEmpty(message="確認用パスワードを入力してください")
	private String passWords;


	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPassWords() {
		return passWords;
	}
	public void setPassWords(String passWords) {
		this.passWords = passWords;
	}
}
