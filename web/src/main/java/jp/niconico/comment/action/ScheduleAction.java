package jp.niconico.comment.action;

import static jp.niconico.comment.util.UrlRewriterUtil.*;
import static org.seasar.framework.util.LongConversionUtil.*;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import jp.niconico.comment.annotation.ana.Allow;
import jp.niconico.comment.dto.LoginDto;
import jp.niconico.comment.entity.Room;
import jp.niconico.comment.entity.Schedule;
import jp.niconico.comment.enums.Permissions;
import jp.niconico.comment.form.ScheduleForm;
import jp.niconico.comment.service.RoomService;
import jp.niconico.comment.service.ScheduleService;

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class ScheduleAction {

	@ActionForm
	@Resource(name = "scheduleForm")
	protected ScheduleForm form;

	@Resource
	public LoginDto loginDto;

	@Resource
	protected ScheduleService scheduleService;

	@Resource
	protected RoomService roomService;

	public List<Schedule> scheduleList;

	@Allow(permission = Permissions.ADMIN)
	@Execute(validator = false)
	public String index() {
		return "list";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeList", input = "/")
	public String list() {
		scheduleList = scheduleService.findAllOrderByStartDate(toLong(form.roomId));

		return "list.jsp";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeCreate", input = "/")
	public String input() {

		return "input.jsp";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeCreate", input = "input", redirect = true)
	public String create() throws ParseException {
		Schedule schedule = Beans.createAndCopy(Schedule.class, form).execute();
		scheduleService.insert(schedule);

		BeanMap params = new BeanMap();
		params.put("roomId", form.roomId);
		return urlFor("Schedule", "list", params);
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validator = false)
	public String edit() {
		Schedule schedule = scheduleService.findById(toLong(form.scheduleId));
		Beans.copy(schedule, form).execute();

		return "showEdit";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeUpdate", input = "/")
	public String showEdit() {

		return "edit.jsp";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeUpdate", input = "showEdit", redirect = true)
	public String update() {
		Schedule schedule = scheduleService.findById(toLong(form.scheduleId));
		Beans.copy(form, schedule).execute();
		scheduleService.update(schedule);

		BeanMap params = new BeanMap();
		params.put("roomId", schedule.roomId);
		return urlFor("Schedule", "list", params);
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeDestroy", input = "list", redirect = true)
	public String destory() {
		Schedule schedule = scheduleService.findById(toLong(form.scheduleId));
		// 削除される前にroomIdを取り出しておく
		BeanMap params = new BeanMap();
		params.put("roomId", schedule.roomId);

		scheduleService.delete(schedule);

		return urlFor("Schedule", "list", params);
	}

	public ActionMessages validateBeforeList() {
		ActionMessages errors = new ActionMessages();

		Room room = roomService.findById(toLong(form.roomId));
		if (room == null) {
			throw new RuntimeException("ルームが見つかりません。id:" + form.roomId);
		}
		return errors;
	}

	public ActionMessages validateBeforeCreate() {
		ActionMessages errors = new ActionMessages();

		Room room = roomService.findById(toLong(form.roomId));
		if (room == null) {
			throw new RuntimeException("ルームが見つかりません。id:" + form.roomId);
		}
		return errors;
	}

	public ActionMessages validateBeforeUpdate() {
		ActionMessages errors = new ActionMessages();

		Schedule schedule = scheduleService.findById(toLong(form.scheduleId));
		if (schedule == null) {
			throw new RuntimeException("スケジュールが見つかりません。id:" + form.scheduleId);
		}
		return errors;
	}

	public ActionMessages validateBeforeDestroy() {
		ActionMessages errors = new ActionMessages();

		Schedule schedule = scheduleService.findById(toLong(form.scheduleId));
		if (schedule == null) {
			throw new RuntimeException("スケジュールが見つかりません。id:" + form.scheduleId);
		}
		return errors;
	}
}
