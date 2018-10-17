package jp.co.ixui.scheduleadjustment.controller.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.co.ixui.scheduleadjustment.controller.login.validator.annotation.PasswordEquals;
import jp.co.ixui.scheduleadjustment.controller.login.validator.annotation.UserExists;



@UserExists
@PasswordEquals(fieldPass="passWord", fieldConfirmPass="passWords")
public class SignupForm {
	
	public SignupForm(){
		
	}
	
	public SignupForm(String empName,String empNum ){
		this.empNum = empNum;
		this.empName = empName;
	}

	@Pattern(regexp = "^[0-9]*$", message="半角数字で入力してください。")
	@Size(max = 5, message="文字数オーバーです")
	@NotEmpty(message="社員番号を入力してください")
	private String empNum;
	
	@Size(max = 15, message="文字数オーバーです")
	@NotEmpty(message="お名前を入力してください")
	private String empName;
	
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message="メールアドレスの形式にしてください。")
	@Size(max = 256, message="文字数オーバーです")
	@NotEmpty(message="メールアドレスを入力してください")
	@Email(message="メールアドレスの形式にしてください")
	private String mailAddress;
	
	@Size(min = 8, message="パスワードは8文字以上で入力してください")
	@Size(max = 256, message="文字数オーバーです")
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
