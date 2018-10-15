package jp.co.ixui.scheduleadjustment.controller.event.validator;

import java.text.DateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.ixui.scheduleadjustment.controller.event.validator.annotation.CheckDate;
/**
 * 候補日フォーマット確認用バリデーション
 *
 *
 *
 */
public class CheckDateValidator implements ConstraintValidator<CheckDate, String>{


	@Override
	public void initialize(CheckDate annotation) {
		
	}
	@Override
	public boolean isValid(String input, ConstraintValidatorContext context) {
		// TODO 自動生成されたメソッド・スタブ
		// 入力されてない場合チェックしない
			if ("".equals(input)) {
				return true;
			}
			
			input = input.replace('-', '/');
			DateFormat format = DateFormat.getDateInstance();
			// 日付/時刻解析を厳密に行うかどうかを設定する。
			format.setLenient(false);
			try {
				format.parse(input);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
	}


