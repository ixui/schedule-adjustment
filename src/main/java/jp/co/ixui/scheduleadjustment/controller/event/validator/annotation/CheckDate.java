package jp.co.ixui.scheduleadjustment.controller.event.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import jp.co.ixui.scheduleadjustment.controller.event.validator.CheckDateValidator;


/**
 * 候補日フォーマット確認用アノテーション
 *
 *
 *
 */
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=CheckDateValidator.class)
@ReportAsSingleViolation
@SuppressWarnings("javadoc")
public @interface CheckDate {
	String message() default "日付の形式で入力してください。　　　";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};


	@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface List
	{
		Period[] value();
	}
}
