package jp.niconico.comment.component.utility;

import static org.seasar.framework.util.DoubleConversionUtil.toDouble;
import static org.seasar.framework.util.FloatConversionUtil.toFloat;
import static org.seasar.framework.util.IntegerConversionUtil.toInteger;
import static org.seasar.framework.util.LongConversionUtil.toLong;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionMessage;
import org.seasar.framework.util.StringConversionUtil;
import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.DoubleRange;
import org.seasar.struts.annotation.DoubleType;
import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.FloatRange;
import org.seasar.struts.annotation.FloatType;
import org.seasar.struts.annotation.IntRange;
import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.LongRange;
import org.seasar.struts.annotation.LongType;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;
import org.seasar.struts.util.MessageResourcesUtil;

/**
 * バリデーションのあれやこれや
 * 
 * @author Yoshitaka Honda
 * 
 */
public class ValidateUtil {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface Validatable {
		String target() default "";
	}
	
	static final String DATE_FORMAT = "yyyyMMdd";
	
	public static boolean isValidDate(String dateStr, boolean permitEmpty) {
		if (permitEmpty && StringUtils.isEmpty(dateStr)) {
			return true;
		}
		
		String[] dateStrElement = dateStr.split("/");
		if (dateStrElement.length != 3) {
			return false;
		}
		String year = dateStrElement[0];
		String month = dateStrElement[1].length() == 2 ? dateStrElement[1] : "0" + dateStrElement[1];
		String day = dateStrElement[2].length() == 2 ? dateStrElement[2] : "0" + dateStrElement[2];
		
		String value = year + month + day;
		
		return isDate(value, DATE_FORMAT);
	}
	
	/**
	 * 対象文字列が指定された日付、時間の書式かどうかを検証する。
	 * 
	 * @param value
	 *            対象文字列（例：2002/03/31、12:10）
	 * @param format
	 *            日付書式（例：yyyy/MM/dd、hh:mm ）
	 * @return value が format の書式であるかどうかを示す boolean。書式であればtrue。
	 */
	public static boolean isDate(String value, String format) {
		if (value == null) {
			value = "";
		}
		
		SimpleDateFormat fmt = null;
		Date d = null;
		try {
			fmt = new SimpleDateFormat(format);
			// あり得ない日付に対し、解析機能を適用し柔軟に解析する。
			fmt.setLenient(true);
			d = fmt.parse(value, new ParsePosition(0));
		} catch (Exception e) {
			return false;
		}
		
		// Date型に変換した後、元の日付と一致するかをチェック
		fmt = new SimpleDateFormat(format);
		fmt.setLenient(true);
		try {
			return fmt.format(d).equals(value);
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 引数の値が上限文字数を超えているかを検証し、超えている場合はメッセージを返します。
	 * 
	 * @param value
	 *            検証する値
	 * @param maxlength
	 *            上限文字数
	 * @return ActionMessage エラーメッセージ
	 */
	public static ActionMessage validate(Maxlength annotation, String value) {
		if (value == null) {
			return null;
		}
		ActionMessage error = null;
		boolean isValid = GenericValidator.maxLength(value, annotation.maxlength());
		if (!isValid) {
			try {
				String arg0 = MessageResourcesUtil.getMessage(annotation.arg0().key());
				String arg1 = StringConversionUtil.toString(annotation.maxlength());
				return MessageGetter.getMsg(annotation.msg(), arg0, arg1);
			} catch (NullPointerException e) {
				// testクラスからの起動に落ちるけど気にせず握りつぶすぜ。
				System.err.println(e);
				return new ActionMessage("エラーです。", false);
			}
		}
		return error;
	}
	
	/**
	 * 引数の値が下限文字数に達しているかを検証し、達していない場合はメッセージを返します。
	 * 
	 * @param value
	 *            検証する値
	 * @param minlength
	 *            下限文字数
	 * @return ActionMessage エラーメッセージ
	 */
	public static ActionMessage validate(Minlength annotation, String value) {
		if (value == null) {
			return null;
		}
		ActionMessage error = null;
		boolean isValid = GenericValidator.minLength(value, annotation.minlength());
		if (!isValid) {
			try {
				String arg0 = MessageResourcesUtil.getMessage(annotation.arg0().key());
				String arg1 = StringConversionUtil.toString(annotation.minlength());
				return MessageGetter.getMsg(annotation.msg(), arg0, arg1);
			} catch (NullPointerException e) {
				// testクラスからの起動に落ちるけど気にせず握りつぶすぜ。
				System.err.println(e);
				return new ActionMessage("エラーです。", false);
			}
		}
		return error;
	}
	
	/**
	 * メッセージ合成
	 * 
	 * @author Yoshitaka Honda
	 * 
	 */
	protected static class MessageGetter {
		protected static String getMsg(Arg arg) {
			String key = arg.key();
			if (StringUtils.isEmpty(key)) {
				return "";
			}
			if (arg.resource()) {
				return MessageResourcesUtil.getMessage(key);
			} else {
				return key;
			}
		}
		
		protected static String[] getMsg(Arg... args) {
			int length = args.length;
			String[] result = new String[length];
			for (int i = 0; i < length; i++) {
				Arg arg = args[i];
				if (arg == null) {
					result[i] = null;
				} else {
					result[i] = getMsg(arg);
				}
			}
			return result;
		}
		
		protected static ActionMessage getMsg(Msg msg, Arg... args) {
			String key = msg.key();
			if (msg.resource()) {
				try {
					return new ActionMessage(key, getMsg(args));
				} catch (NullPointerException e) {
					// メッセージリソースが見つからない場合は握りつぶす
					return new ActionMessage(key, false);
				}
			} else {
				return new ActionMessage(key, false);
			}
		}
		
		protected static ActionMessage getMsg(Msg msg, String... args) {
			String key = msg.key();
			if (msg.resource()) {
				try {
					return new ActionMessage(key, args);
				} catch (NullPointerException e) {
					// メッセージリソースが見つからない場合は握りつぶす
					return new ActionMessage(key, false);
				}
			} else {
				return new ActionMessage(key, false);
			}
		}
	}
	
	/**
	 * 引数の値が空でないかを検証し、空の場合はメッセージを返します。
	 * 
	 * @param name
	 *            検証するプロパティ名
	 * @param value
	 *            検証する値
	 * @return ActionMessage エラーメッセージ
	 */
	public static ActionMessage validate(Required annotation, String value) {
		boolean valid = !GenericValidator.isBlankOrNull(value);
		ActionMessage error = null;
		if (!valid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0());
		}
		return error;
	}
	
	public static ActionMessage validate(IntegerType annotation, String value) {
		boolean valid = GenericValidator.isInt(value);
		if (!valid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0());
		}
		return null;
	}
	
	public static ActionMessage validate(LongType annotation, String value) {
		boolean isValid = GenericValidator.isLong(value);
		if (!isValid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0());
		}
		return null;
	}
	
	public static ActionMessage validate(FloatType annotation, String value) {
		boolean valid = GenericValidator.isFloat(value);
		if (!valid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0());
		}
		return null;
	}
	
	public static ActionMessage validate(DoubleType annotation, String value) {
		boolean valid = GenericValidator.isDouble(value);
		if (!valid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0());
		}
		return null;
	}
	
	public static ActionMessage validate(LongRange annotation, String value) {
		boolean valid = GenericValidator.isInRange(toLong(value), annotation.min(), annotation.max());
		if (!valid) {
			try {
				String arg0 = MessageResourcesUtil.getMessage(annotation.arg0().key());
				String arg1 = StringConversionUtil.toString(annotation.min());
				String arg2 = StringConversionUtil.toString(annotation.max());
				return MessageGetter.getMsg(annotation.msg(), arg0, arg1, arg2);
			} catch (NullPointerException e) {
				// testクラスからの起動に落ちるけど気にせず握りつぶすぜ。
				System.err.println(e);
				return new ActionMessage("エラーです。", false);
			}
		}
		return null;
	}
	
	public static ActionMessage validate(IntRange annotation, String value) {
		boolean valid = GenericValidator.isInRange(toInteger(value), annotation.min(), annotation.max());
		if (!valid) {
			String arg0 = MessageResourcesUtil.getMessage(annotation.arg0().key());
			return MessageGetter.getMsg(annotation.msg(), arg0, StringConversionUtil.toString(annotation.min()),
					StringConversionUtil.toString(annotation.max()));
		}
		return null;
	}
	
	public static ActionMessage validate(FloatRange annotation, String value) {
		boolean valid = GenericValidator
				.isInRange(toFloat(value), toFloat(annotation.min()), toFloat(annotation.max()));
		if (!valid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0(), annotation.arg1(), annotation.arg2());
		}
		return null;
	}
	
	public static ActionMessage validate(DoubleRange annotation, String value) {
		boolean valid = GenericValidator.isInRange(toDouble(value), toDouble(annotation.min()),
				toDouble(annotation.max()));
		if (!valid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0(), annotation.arg1(), annotation.arg2());
		}
		return null;
	}
	
	public static ActionMessage validate(EmailType annotation, String value) {
		boolean valid = GenericValidator.isEmail(value);
		if (!valid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0());
		}
		return null;
	}
	
	public static ActionMessage validate(Mask annotation, String value) {
		boolean valid = GenericValidator.matchRegexp(value, annotation.mask());
		if (!valid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0());
		}
		return null;
	}
	
	public static ActionMessage validate(DateType annotation, String value) {
		boolean isStrict = StringUtils.isNotEmpty(annotation.datePatternStrict());
		String pattern = isStrict ? annotation.datePatternStrict() : annotation.datePattern();
		
		boolean valid = GenericValidator.isDate(value, pattern, isStrict);
		if (!valid) {
			return MessageGetter.getMsg(annotation.msg(), annotation.arg0());
		}
		return null;
	}
	
	public static IntegerType getIntegerType(Field f) {
		for (Annotation annotation : f.getAnnotations()) {
			boolean isIntegerType = (annotation instanceof IntegerType);
			if (isIntegerType) {
				return (IntegerType) annotation;
			}
		}
		return null;
	}
	
	public static LongType getLongType(Field f) {
		for (Annotation annotation : f.getAnnotations()) {
			boolean isLongType = (annotation instanceof LongType);
			if (isLongType) {
				return (LongType) annotation;
			}
		}
		return null;
	}
	
	public static DoubleType getDoubleType(Field f) {
		for (Annotation annotation : f.getAnnotations()) {
			boolean isDoubleType = (annotation instanceof DoubleType);
			if (isDoubleType) {
				return (DoubleType) annotation;
			}
		}
		return null;
	}
	
	public static FloatType getFloatType(Field f) {
		for (Annotation annotation : f.getAnnotations()) {
			boolean isFloatType = (annotation instanceof FloatType);
			if (isFloatType) {
				return (FloatType) annotation;
			}
		}
		return null;
	}
}
