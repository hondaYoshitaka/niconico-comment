package jp.niconico.comment.annotation.ana;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jp.niconico.comment.enums.Permissions;

/**
 * 許可を表すアノテーション
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Allow {
	/**
	 * 権限
	 */
	Permissions permission();
}
