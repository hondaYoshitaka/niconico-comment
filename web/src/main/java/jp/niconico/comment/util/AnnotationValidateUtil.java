package jp.niconico.comment.util;

import static jp.niconico.comment.util.ValidateUtil.*;
import static org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

import jp.niconico.comment.util.ValidateUtil.Validatable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.StringConversionUtil;
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
import org.seasar.struts.annotation.Required;

/**
 * フィールドのアノテーションを見て、対応するバリデーションを実行するクラス。
 * 
 * @author Yoshitaka Honda
 * 
 */
public class AnnotationValidateUtil {
	
	public static class AnnotationValidator {
		protected String target;
		protected boolean recursive;
		
		public AnnotationValidator(String target) {
			this(target, false);
		}
		
		AnnotationValidator(boolean recursive) {
			this("", recursive);
		}
		
		AnnotationValidator(String target, boolean recursive) {
			super();
			this.target = target;
			this.recursive = recursive;
		}
		
		/**
		 * エラーのプロパティに名前空間を付与する
		 * 
		 * @param errors
		 * @param schema
		 * @return
		 */
		protected static ActionMessages addPropertyNameSpace(ActionMessages errors, String nameSpace) {
			ActionMessages result = new ActionMessages();
			for (Iterator<?> propIter = errors.properties(); propIter.hasNext();) {
				String property = (String) propIter.next();
				String newProperty = String.format("%s.%s", nameSpace, property);
				for (Iterator<?> errIter = errors.get(property); errIter.hasNext();) {
					ActionMessage error = (ActionMessage) errIter.next();
					result.add(newProperty, error);
				}
			}
			return result;
		}
		
		public ActionMessages execute(Object dto) {
			ActionMessages errors = new ActionMessages();
			BeanDesc desc = BeanDescFactory.getBeanDesc(dto.getClass());
			for (int i = 0; i < desc.getFieldSize(); i++) {
				Field f = desc.getField(i);
				Object value = FieldUtil.get(f, dto);
				
				if (recursive) {
					boolean validatable = (f.getAnnotation(Validatable.class) != null);
					if (validatable) {
						String nameSpace = f.getName();
						// TODO リストの時の名前空間解決
						ActionMessages innerErrors = addPropertyNameSpace(execute(value), nameSpace);
						errors.add(innerErrors);
						continue;
					}
				}
				
				if (value != null && value instanceof Collection) {
					for (Object e : (Collection<?>) value) {
						errors.add(executeField(f, e));
					}
				} else {
					errors.add(executeField(f, value));
				}
			}
			
			return errors;
		}
		
		protected String nameSpace = null;
		
		public String getNameSpace() {
			return nameSpace;
		}
		
		public void setNameSpace(String nameSpace) {
			this.nameSpace = nameSpace;
		}
		
		protected ActionMessages createErrors(String property, ActionMessage err) {
			ActionMessages errors = new ActionMessages();
			if (StringUtils.isNotBlank(nameSpace)) {
				property = nameSpace + "." + property;
			}
			if (StringUtils.isEmpty(property)) {
				property = GLOBAL_MESSAGE;
			}
			errors.add(property, err);
			return errors;
		}
		
		public ActionMessages executeField(Field f, String value) {
			ActionMessages errors = new ActionMessages();
			Required required = f.getAnnotation(Required.class);
			if (required != null) {
				if (isTarget(required.target())) {
					ActionMessage err = validate(required, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			
			// 項目に値がない場合、その他のバリデーションはスキップする
			boolean blankOrNull = GenericValidator.isBlankOrNull(value);
			if (blankOrNull) {
				return errors;
			}
			
			IntegerType integerType = getIntegerType(f);
			if (integerType != null) {
				ActionMessage err = validate(integerType, value);
				if (err != null) {
					return createErrors(f.getName(), err);
				}
			}
			LongType longType = getLongType(f);
			if (longType != null) {
				ActionMessage err = validate(longType, value);
				if (err != null) {
					return createErrors(f.getName(), err);
				}
			}
			FloatType floatType = getFloatType(f);
			if (floatType != null) {
				ActionMessage err = validate(floatType, value);
				if (err != null) {
					return createErrors(f.getName(), err);
				}
			}
			DoubleType doubleType = getDoubleType(f);
			if (doubleType != null) {
				ActionMessage err = validate(doubleType, value);
				if (err != null) {
					return createErrors(f.getName(), err);
				}
			}
			
			Maxlength max = f.getAnnotation(Maxlength.class);
			if (max != null) {
				if (isTarget(max.target())) {
					ActionMessage err = validate(max, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			
			Minlength min = f.getAnnotation(Minlength.class);
			if (min != null) {
				if (isTarget(min.target())) {
					ActionMessage err = validate(min, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			LongRange longRange = f.getAnnotation(LongRange.class);
			if (longRange != null) {
				if (isTarget(longRange.target())) {
					ActionMessage err = validate(longRange, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			IntRange intRange = f.getAnnotation(IntRange.class);
			if (intRange != null) {
				if (isTarget(intRange.target())) {
					ActionMessage err = validate(intRange, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			
			FloatRange floatRange = f.getAnnotation(FloatRange.class);
			if (floatRange != null) {
				if (isTarget(floatRange.target())) {
					ActionMessage err = validate(floatRange, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			DoubleRange doubleRange = f.getAnnotation(DoubleRange.class);
			if (doubleRange != null) {
				if (isTarget(doubleRange.target())) {
					ActionMessage err = validate(doubleRange, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			EmailType emailType = f.getAnnotation(EmailType.class);
			if (emailType != null) {
				if (isTarget(emailType.target())) {
					ActionMessage err = validate(emailType, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			Mask mask = f.getAnnotation(Mask.class);
			if (mask != null) {
				if (isTarget(mask.target())) {
					ActionMessage err = validate(mask, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			DateType dateType = f.getAnnotation(DateType.class);
			if (dateType != null) {
				if (isTarget(dateType.target())) {
					ActionMessage err = validate(dateType, value);
					if (err != null) {
						return createErrors(f.getName(), err);
					}
				}
			}
			return errors;
		}
		
		protected ActionMessages executeField(Field f, Object target) {
			ActionMessages errors = new ActionMessages();
			try {
				errors = executeField(f, StringConversionUtil.toString(target));
			} catch (EmptyRuntimeException e) {
				// メッセージリソースが無いとコケることがある
				ActionMessage err = new ActionMessage("予期せぬエラーが発生しました。", false);
				errors.add(f.getName(), err);
			}
			return errors;
		}
		
		protected boolean isTarget(String targets) {
			if (StringUtils.isBlank(targets)) {
				return true;
			}
			// TODO スペース除去する
			for (String target : StringUtils.split(targets, ",")) {
				if (StringUtils.equals(this.target, target)) {
					return true;
				}
			}
			return false;
		}
		
	}
	
	/**
	 * 再帰的にバリデーションを実行する
	 * 
	 * @param object
	 * @return
	 */
	public static ActionMessages validateRecursive(Object object) {
		AnnotationValidator validator = new AnnotationValidator(true);
		return validator.execute(object);
	}
	
	public static ActionMessages validateRecursive(Object object, String nameSpace) {
		AnnotationValidator validator = new AnnotationValidator(true);
		validator.setNameSpace(nameSpace);
		return validator.execute(object);
	}
}