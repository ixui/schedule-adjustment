package jp.co.ixui.scheduleadjustment.controller.login.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.scheduleadjustment.controller.login.SignupForm;
import jp.co.ixui.scheduleadjustment.controller.login.validator.annotation.UserExists;
import jp.co.ixui.scheduleadjustment.domain.Emp;
import jp.co.ixui.scheduleadjustment.mapper.EmployeeMapper;


/**
 * ユーザー登録時の重複確認用のバリデータ
 *
 *
 *
 */
public class UserExistsValidator implements ConstraintValidator<UserExists, SignupForm> {

	@Autowired
	EmployeeMapper employeeMapper;

	@Override
	public void initialize(UserExists userExists) {
	}

	/**
	 * チェック
	 */
	@Override
	public boolean isValid(SignupForm value, ConstraintValidatorContext cxt) {

		String empNo = value.getEmpNum();

		if (empNo == null) {
			// FormDTOにて@NotNullを使用するべき項目
			return true;
		}

		// 社員情報を取得し存在すればエラー
		Emp emp = this.employeeMapper.selectUser(empNo);
		if (null != emp) {
			return false;
		}

		return true;
	}

}
