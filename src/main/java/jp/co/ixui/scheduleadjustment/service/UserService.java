package jp.co.ixui.scheduleadjustment.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	
	/**
	 * セッションに社員番号が存在するか確認する
	 *
	 * @param request
	 */
	public boolean isValidUserSession(HttpServletRequest request) {

		// セッションを取得し取得できなかった場合はタイムアウトとみなす
		HttpSession currentSession = request.getSession(false);
		if (currentSession == null) {
			return false;
		}

		// リクエストに含まれるセッションIDでセッションを再取得し
		// 取得できないもしくは保持しているセッションと異なる場合はタイムアウトとみなす
		String requestSession = request.getRequestedSessionId();
		if( currentSession == null ||
				requestSession == null ||
				!request.isRequestedSessionIdValid() ||
				!requestSession.equals(currentSession.getId())){

			return false;
		}

		// セッションに保存されている認証情報から社員番号を取得する
		String empNo = getEmpNoFromAuthentication();

		if (empNo == null ||
			"".equals(empNo) ||
			"anonymousUser".equals(empNo)) {

			return false;
		}

		return true;
	}

	/**
	 * セッションに保存された認証情報から社員番号を取得する
	 *
	 * @param request
	 * @param loginDTO
	 */
	@SuppressWarnings("static-method")
	public String getEmpNoFromAuthentication() {

		String empNo = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			empNo = ((UserDetails)principal).getUsername();
		} else {
			empNo = principal.toString();
		}
		return empNo;
	}


}
