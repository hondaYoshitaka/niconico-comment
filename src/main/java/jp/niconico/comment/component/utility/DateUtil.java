package jp.niconico.comment.component.utility;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.seasar.framework.util.DateConversionUtil;
import org.seasar.framework.util.SqlDateConversionUtil;
import org.seasar.framework.util.TimestampConversionUtil;
import org.seasar.struts.util.RequestUtil;

public class DateUtil extends DateUtils {
	
	private static final String CURRENT_DATE_KEY = "jp.niconico.comment.currentDate";
	
	/**
	 * Dateで現在日付を取得する
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		HttpServletRequest request = RequestUtil.getRequest();
		if (request == null) {
			return new Date(System.currentTimeMillis());
		}
		// 同一リクエスト上において
		// 取得するタイミングで現在時刻がずれるのを防ぐ
		Date currentDate = DateConversionUtil.toDate(request.getAttribute(CURRENT_DATE_KEY));
		
		if (currentDate == null) {
			currentDate = new Date(System.currentTimeMillis());
			// HACK GMTだけに対応している。あまりいい解決策ではない。
			if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT")) {
				currentDate = DateUtils.addHours(currentDate, 9);
			}
			request.setAttribute(CURRENT_DATE_KEY, currentDate);
		}
		return currentDate;
	}
	
	/**
	 * java.sql.dateで現在日付を取得する
	 * 
	 * @return
	 */
	public static java.sql.Date getCurrentSQLDate() {
		return SqlDateConversionUtil.toDate(getCurrentDate());
	}
	
	/**
	 * Timestampで現在付を取得する
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTimestamp() {
		Date date = getCurrentDate();
		return TimestampConversionUtil.toTimestamp(date);
	}
	
	/**
	 * Calenderで現在日付を取得する
	 * 
	 * @return
	 */
	public static Calendar getCurrentCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		return cal;
	}
	
	/**
	 * Dateで現在年月日を取得する
	 * 
	 * @return
	 */
	public static Date getCurrentYMD() {
		Date currentDate = getCurrentDate();
		return DateUtils.truncate(currentDate, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 月を取得する
	 * 
	 * @return
	 */
	public static Integer getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 日を取得する
	 * 
	 * @return
	 */
	public static Integer getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 差分日数を返す。
	 */
	public static Long diffDate(Date from, Date to) {
		long diffDate = (to.getTime() - from.getTime()) / MILLIS_PER_DAY;
		return diffDate;
	}
}