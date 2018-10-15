package jp.co.ixui.scheduleadjustment.controller.login.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import jp.co.ixui.scheduleadjustment.controller.login.validator.annotation.PasswordEquals;
import jp.co.ixui.scheduleadjustment.mapper.EmployeeMapper;


/**
 * ユーザ登録時のパスワードが一致しているか確認するバリデータ
 *
 *
 *
 */
public class PasswordEqualsValidator implements ConstraintValidator<PasswordEquals, Object> {

	@Autowired
	EmployeeMapper employeeMapper;

	private String fieldPass;
	private String fieldConfirmPass;
	private String message;

	@Override
	public void initialize(PasswordEquals annotation) {
		this.fieldPass = annotation.fieldPass();
		this.fieldConfirmPass = annotation.fieldConfirmPass();
		this.message = annotation.message();
	}

	/**
	 * チェック
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext cxt) {

		// BeanWrapper経由でフォームの値を取得
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		String password = (String)beanWrapper.getPropertyValue(this.fieldPass);
		String confirmPassword = (String)beanWrapper.getPropertyValue(this.fieldConfirmPass);

		if (password == null || confirmPassword == null) {
			// FormDTOにて@NotNullを使用するべき項目
			return true;
		}

		if (!password.equals(confirmPassword)) {
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate(this.message)
			.addConstraintViolation();
			return false;
		}

		return true;
	}

}
