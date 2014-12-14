package jp.niconico.comment.form;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.Required;

public class ScheduleForm {
	@Required(target = "edit,update,destory")
	public String scheduleId;

	@Required(target = "create")
	public String roomId;

	@Required(target = "create,update")
	public String scheduleName;

	@Required(target = "create,update")
	@DateType(datePattern = "yyyy/MM/dd")
	public String startDate;

	@Required(target = "create,update")
	@DateType(datePattern = "HH:mm")
	public String startTime;

	@Required(target = "create,update")
	@DateType(datePattern = "yyyy/MM/dd")
	public String endDate;

	@Required(target = "create,update")
	@DateType(datePattern = "HH:mm")
	public String endTime;

	public String detail;

	public void setStartDatetime(Timestamp startDatetime) {
		if (startDatetime == null) {
			return;
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		this.startDate = df.format(startDatetime);

		DateFormat df2 = new SimpleDateFormat("HH:mm");
		this.startTime = df2.format(startDatetime);
	}

	public void setEndDatetime(Timestamp endDatetime) {
		if (endDatetime == null) {
			return;
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		this.endDate = df.format(endDatetime);

		DateFormat df2 = new SimpleDateFormat("HH:mm");
		this.endTime = df2.format(endDatetime);
	}

	public Timestamp getStartDatetime() throws ParseException {
		if (StringUtils.isEmpty(this.startDate) || StringUtils.isEmpty(this.startTime)) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String datetimeStr = String.format("%s %s", this.startDate, this.startTime);
		long time = df.parse(datetimeStr).getTime();

		// JSTとの差分を補う
		int jstOffset = TimeZone.getTimeZone("JST").getRawOffset();
		int sysOffset = TimeZone.getDefault().getRawOffset();
		int diff = (sysOffset - jstOffset);
		time += diff;

		return new Timestamp(time);
	}

	public Timestamp getEndDatetime() throws ParseException {
		if (StringUtils.isEmpty(this.endDate) || StringUtils.isEmpty(this.endTime)) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String datetimeStr = String.format("%s %s", this.endDate, this.endTime);
		long time = df.parse(datetimeStr).getTime();

		// JSTとの差分を補う
		int jstOffset = TimeZone.getTimeZone("JST").getRawOffset();
		int sysOffset = TimeZone.getDefault().getRawOffset();
		int diff = (sysOffset - jstOffset);
		time += diff;

		return new Timestamp(time);
	}
}
