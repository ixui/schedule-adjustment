package jp.co.ixui.scheduleadjustment.domain;

public class Emp {
	private String empNum;
	private String empName;
	private String mailAddress;
	private String passWord;
	

	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
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

	public Emp authEmp(String empNum, String passWord) {
		// TODO 自動生成されたメソッド・スタブ
		this.empNum = empNum;
		this.passWord = passWord;
		return null;
	}

}
