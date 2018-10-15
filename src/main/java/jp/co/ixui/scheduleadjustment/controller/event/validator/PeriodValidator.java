package jp.co.ixui.scheduleadjustment.controller.event.validator;



import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jp.co.ixui.scheduleadjustment.controller.event.EventController;
import jp.co.ixui.scheduleadjustment.controller.event.validator.annotation.Period;
/**
 * 候補日前後確認用バリデーション
 *
 *
 *
 */
public class PeriodValidator implements ConstraintValidator<Period, Object>{
	public static Logger log = LoggerFactory.getLogger(EventController.class);
		private String fieldFrom;
		private String fieldTo;
		private String message;
	
		@Override
		public void initialize(Period annotation) {
			this.fieldFrom = annotation.fieldFrom();
			this.fieldTo = annotation.fieldTo();
			this.message = annotation.message();
		}
	
		public boolean isValid(Object form, ConstraintValidatorContext context) {
			boolean ret = true;
			if(form == null){
				ret = true;
			}else{
				BeanWrapper beanWrapper = new BeanWrapperImpl(form);
				String start = (String) beanWrapper.getPropertyValue(fieldFrom);
				String end = (String) beanWrapper.getPropertyValue(fieldTo);
				try{
					Pattern ptn = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})");
					Matcher mchStart = ptn.matcher(start);
					if (mchStart.find()) {
						log.debug("一致しました。");
					} else {
						log.debug("一致しません。");
					}
					LocalDate startDateObj = LocalDate.of(Integer.valueOf(mchStart.group(1)),
							Integer.valueOf(mchStart.group(2)), Integer.valueOf(mchStart.group(3)));
					Matcher mchEnd = ptn.matcher(end);
					if (mchEnd.find()) {
						log.debug("一致しました。");
					} else {
						log.debug("一致しません。");
					}
					LocalDate endDateObj = LocalDate.of(Integer.valueOf(mchEnd.group(1)), 
							Integer.valueOf(mchEnd.group(2)),Integer.valueOf(mchEnd.group(3)));
		
			if(endDateObj.compareTo(startDateObj) >= 0){
					ret = true;
				}else{
					ret = false;
				}

				if(ret){
					return true;
				}else{
					context.disableDefaultConstraintViolation();   
					context
					.buildConstraintViolationWithTemplate(message)
					.addConstraintViolation();
					return false;
				}

			}catch(Exception e){
				return true;
				}
			}
			return false;
		}
	}
