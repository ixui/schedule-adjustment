package jp.co.ixui.scheduleadjustment.controller.event.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import jp.co.ixui.scheduleadjustment.controller.event.validator.PeriodValidator;

/**
 * 候補日前後確認用アノテーション
 *
 *
 *
 */
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy={PeriodValidator.class})
@ReportAsSingleViolation
public @interface Period {
	String message() default "開始日は終了日以前でなくてはいけません。";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fieldFrom() default "from";
	String fieldTo() default "to";

	@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface List
	{
		Period[] value();
	}
}
