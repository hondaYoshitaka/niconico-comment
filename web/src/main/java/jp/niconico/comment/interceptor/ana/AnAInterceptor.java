package jp.niconico.comment.interceptor.ana;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import jp.niconico.comment.annotation.ana.Allow;
import jp.niconico.comment.annotation.ana.Deny;
import jp.niconico.comment.dto.LoginDto;
import jp.niconico.comment.exception.ana.NotAuthenticatedException;
import jp.niconico.comment.exception.ana.NotAuthorizedException;
import jp.niconico.comment.util.OnetimeMsgUtil;
import jp.niconico.comment.util.ana.AnAUtil;
import jp.niconico.comment.util.ana.AnAUtil.AccessCheck;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.hotdeploy.HotdeployUtil;
import org.seasar.framework.util.FieldUtil;
import org.seasar.struts.annotation.Execute;

/**
 * 認証と認可を行うインターセプタ
 */
public class AnAInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		final Method method = invocation.getMethod();
		if (getAnnotation(Execute.class, method) == null) {
			return invocation.proceed();
		}
		final Allow allow = getAnnotation(Allow.class, method);
		final Deny deny = getAnnotation(Deny.class, method);
		if (allow == null && deny == null) {
			return invocation.proceed();
		}
		final Object action = invocation.getThis();
		LoginDto dto = getLoginDto(action);
		
		AccessCheck authorization = AnAUtil.authorization(dto, (allow != null ? allow.permission() : null),
				(deny != null ? deny.permission() : null));
		switch (authorization) {
			case NotAuthenticated:
				OnetimeMsgUtil.saveErrors("errors.requireLogin");
				throw new NotAuthenticatedException();
			case Success:
				return invocation.proceed();
			case NotAuthorized:
				OnetimeMsgUtil.saveErrors("errors.requireLogin");
				throw new NotAuthorizedException(dto, (allow != null ? allow.permission() : null));
			default:
				OnetimeMsgUtil.saveErrors("errors.requireLogin");
				throw new NotAuthorizedException(dto, (allow != null ? allow.permission() : null));
		}
	}
	
	/**
	 * 指定のアノテーションを抽出する
	 * 
	 * @param annotationClass
	 *            アノテーションのクラス
	 * @param method
	 *            チェックするメソッド
	 * @return アノテーション。付いていない場合null
	 */
	@SuppressWarnings("unchecked")
	protected <A extends Annotation> A getAnnotation(Class<A> annotationClass, Method method) {
		if (HotdeployUtil.isHotdeploy()) {
			final String annotationName = annotationClass.getName();
			for (Annotation annotation : method.getDeclaredAnnotations()) {
				if (annotationName.equals(annotation.annotationType().getName())) {
					return (A) HotdeployUtil.rebuildValue(annotation);
				}
			}
			return null;
		}
		return method.getAnnotation(annotationClass);
	}
	
	/**
	 * ログインユーザーのDtoを抽出する
	 * 
	 * @param action
	 * @return ログインユーザーのDto。取れない場合はnull
	 */
	protected LoginDto getLoginDto(Object action) {
		final BeanDesc beanDesc = BeanDescFactory.getBeanDesc(action.getClass());
		Object dto = null;
		final String propertyName = "loginDto";
		if (beanDesc.hasPropertyDesc(propertyName)) {
			PropertyDesc pd = beanDesc.getPropertyDesc(propertyName);
			if (pd.isReadable()) {
				dto = pd.getValue(action);
			}
		} else if (beanDesc.hasField(propertyName)) {
			Field f = beanDesc.getField(propertyName);
			if (!f.isAccessible()) {
				f.setAccessible(true);
			}
			dto = FieldUtil.get(f, action);
		}
		if (dto instanceof LoginDto) {
			return (LoginDto) HotdeployUtil.rebuildValue(dto);
		}
		return null;
	}
}
