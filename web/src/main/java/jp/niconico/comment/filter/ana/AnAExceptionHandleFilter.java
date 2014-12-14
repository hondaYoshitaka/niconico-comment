package jp.niconico.comment.filter.ana;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.niconico.comment.exception.ana.NotAuthenticatedException;
import jp.niconico.comment.exception.ana.NotAuthorizedException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 認証・認可チェックで発生したエラーを処理する
 */
public class AnAExceptionHandleFilter implements Filter {
	/** デフォルトのエラーページ */
	public static final String DEFAULT_ERRORPAGE = "/error401.do";

	protected Logger logger = Logger.getLogger("systemLog");
	/** 認証エラー時のログインページへのパス */
	protected String loginPage;
	/** ログインページにリダイレクトするかどうか */
	protected boolean loginRedirect;
	/** 権限エラー時のエラーページのパス */
	protected String authorizedErrorPage;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		loginPage = StringUtils.defaultString(config.getInitParameter("loginPage"));
		loginRedirect = "true".equalsIgnoreCase(StringUtils.defaultIfEmpty(config.getInitParameter("loginRedirect"),
				"true"));
		authorizedErrorPage = StringUtils.defaultIfEmpty(config.getInitParameter("authorizedErrorPage"),
				DEFAULT_ERRORPAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
		loginPage = null;
		authorizedErrorPage = null;
		loginRedirect = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		try {
			chain.doFilter(request, response);
		} catch (NotAuthenticatedException e) {
			handleNotAuthenticatedException(e, (HttpServletRequest) request, (HttpServletResponse) response);
		} catch (NotAuthorizedException e) {
			handleNotAuthorizedException(e, (HttpServletRequest) request, (HttpServletResponse) response);
		} catch (ServletException se) {
			Throwable causeException = se.getCause();
			if (causeException == null) {
				throw se;
			}
			if (causeException instanceof NotAuthenticatedException) {
				handleNotAuthenticatedException((NotAuthenticatedException) causeException,
						(HttpServletRequest) request, (HttpServletResponse) response);
			} else if (causeException instanceof NotAuthorizedException) {
				handleNotAuthorizedException((NotAuthorizedException) causeException, (HttpServletRequest) request,
						(HttpServletResponse) response);
			} else {
				throw se;
			}
		}
	}

	protected void handleNotAuthenticatedException(NotAuthenticatedException e, HttpServletRequest request,
			HttpServletResponse response) {
		final boolean ajax = "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
		if (logger.isInfoEnabled()) {
			logger.info("認証エラーが発生しました:" + request.getAttribute("javax.servlet.forward.request_uri"));
		}
		try {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			if (ajax) {
				return;
			}
			if (loginRedirect) {
				response.sendRedirect(loginPage.replace("{contextPath}", request.getContextPath()));
			} else {
				request.getRequestDispatcher(loginPage).forward(request, response);
			}
		} catch (Exception e1) {
			logger.warn("認証エラーハンドル中にエラーが発生しました", e1);
			throw e;
		}
	}

	protected void handleNotAuthorizedException(NotAuthorizedException e, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("権限エラーが発生しました " + e.getMessage());
		if (!response.isCommitted()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		try {
			request.getRequestDispatcher(authorizedErrorPage).forward(request, response);
		} catch (Exception e1) {
			logger.warn("認可エラーハンドル中にエラーが発生しました", e1);
			throw e;
		}
	}

}
