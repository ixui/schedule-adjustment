package jp.co.ixui.scheduleadjustment.controller.login.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import jp.co.ixui.scheduleadjustment.controller.login.validator.UserExistsValidator;



/**
 * ユーザー重複確認用のアノテーション
 *
 *
 *
 */
@Documented
@Constraint(validatedBy = UserExistsValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@SuppressWarnings("javadoc")
public @interface UserExists {

	String message() default "既に登録されているユーザが存在します";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.TYPE, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		UserExists[] value();
	}
}
