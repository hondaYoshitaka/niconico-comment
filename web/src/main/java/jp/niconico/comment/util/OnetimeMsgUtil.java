package jp.niconico.comment.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;

public class OnetimeMsgUtil {

	public static final String SUCCESS_MESSAGES_ALIAS = "MESSAGES_SUCCESS";

	/**
	 * @see #save(HttpServletRequest, String...)
	 */
	public static void save(String... messageKeys) {
		save(RequestUtil.getRequest(), messageKeys);
	}

	/**
	 * ActionMessageをセッションスコープに格納します。
	 *
	 * @param request
	 * @param messageKeys
	 */
	public static void save(HttpServletRequest request, String... messageKeys) {
		ActionMessages messages = new ActionMessages();

		for (String key : messageKeys) {
			messages.add(SUCCESS_MESSAGES_ALIAS, new ActionMessage(key, true));
		}
		ActionMessagesUtil.saveMessages(request.getSession(), messages);
	}

	/**
	 * @see #saveErrors(HttpServletRequest, String...)
	 */
	public static void saveErrors(String... messageKeys) {
		saveErrors(RequestUtil.getRequest(), messageKeys);
	}

	/**
	 * エラーメッセージをセッションスコープに格納します。
	 *
	 * @param request
	 * @param messageKeys
	 */
	public static void saveErrors(HttpServletRequest request, String... messageKeys) {
		ActionMessages errors = new ActionMessages();
		for (String key : messageKeys) {
			errors.add(Globals.ERROR_KEY, new ActionMessage(key, true));
		}
		ActionMessagesUtil.saveErrors(request.getSession(), errors);
	}
}
