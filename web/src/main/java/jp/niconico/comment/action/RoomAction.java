package jp.niconico.comment.action;

import static jp.niconico.comment.util.UrlRewriterUtil.*;
import static org.apache.struts.action.ActionMessages.*;
import static org.seasar.framework.util.LongConversionUtil.*;

import java.util.List;

import javax.annotation.Resource;

import jp.niconico.comment.annotation.ana.Allow;
import jp.niconico.comment.dto.LoginDto;
import jp.niconico.comment.entity.LikeRoom;
import jp.niconico.comment.entity.Room;
import jp.niconico.comment.enums.Permissions;
import jp.niconico.comment.form.RoomForm;
import jp.niconico.comment.service.LikeRoomService;
import jp.niconico.comment.service.RoomService;
import jp.niconico.comment.service.UserService;
import jp.niconico.comment.util.DateUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.RequestUtil;

public class RoomAction {

	@Resource
	public LoginDto loginDto;

	@ActionForm
	@Resource(name = "roomForm")
	public RoomForm form;

	@Resource
	protected RoomService roomService;

	@Resource
	protected LikeRoomService likeRoomService;

	@Resource
	protected UserService userService;

	public Room room;

	@Allow(permission = Permissions.ADMIN)
	@Execute(validator = false)
	public String input() {
		return "input.jsp";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateDuplicateName", input = "input", redirect = true)
	public String create() {
		Room room = Beans.createAndCopy(Room.class, form).execute();
		roomService.insert(room);

		return urlFor("Index", "index");
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeUpdate", input = "/")
	public String edit() {
		Room room = roomService.findById(toLong(form.roomId));
		Beans.copy(room, form).execute();

		return "showEdit";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validator = false)
	public String showEdit() {
		return "edit.jsp";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeUpdate,validateDuplicateName", input = "showEdit", redirect = true)
	public String update() {
		Room room = Beans.createAndCopy(Room.class, form).execute();
		roomService.update(room);

		return urlFor("Index", "index");
	}

	@Allow(permission = Permissions.LOGIN)
	@Execute(validate = "validateBeforeShow", input = "/")
	public String show() {
		room = roomService.findById(toLong(form.roomId));

		return "show.jsp";
	}

	@Allow(permission = Permissions.LOGIN)
	@Execute(validate = "validateBeforeLike", input = "/", redirect = true)
	public String like() {
		LikeRoom like = Beans.createAndCopy(LikeRoom.class, form).execute();

		String sessionId = RequestUtil.getRequest().getSession().getId();
		like.likedBy = sessionId;
		like.likedDatetime = DateUtil.getCurrentTimestamp();

		likeRoomService.insert(like);

		return urlFor("Index", "index");
	}

	/**
	 * ルーム名が重複していないか検証します。
	 * 
	 * @return
	 */
	public ActionMessages validateDuplicateName() {
		ActionMessages errors = new ActionMessages();

		BeanMap conditions = new BeanMap();
		conditions.put("roomName", form.roomName);
		List<Room> rooms = roomService.findByCondition(conditions);
		if (CollectionUtils.isNotEmpty(rooms)) {
			errors.add(GLOBAL_MESSAGE, new ActionMessage("既に同じ名前のルームが存在します。", false));
		}
		return errors;
	}

	/**
	 * 詳細画面に表示する前に検証を行います。
	 * 
	 * @return
	 */
	public ActionMessages validateBeforeShow() {
		ActionMessages errors = new ActionMessages();

		Room room = roomService.findById(toLong(form.roomId));
		if (room == null) {
			throw new RuntimeException("ルームが見つかりません。id:" + form.roomId);
		}
		return errors;
	}

	public ActionMessages validateBeforeLike() {
		ActionMessages errors = new ActionMessages();

		Room room = roomService.findById(toLong(form.roomId));
		if (room == null) {
			throw new RuntimeException("ルームが見つかりません。id:" + form.roomId);
		}
		BeanMap conditions = new BeanMap();

		conditions.put("roomId", form.roomId);
		String sessionId = RequestUtil.getRequest().getSession().getId();
		conditions.put("likedBy", sessionId);

		List<LikeRoom> likes = likeRoomService.findByCondition(conditions);
		if (CollectionUtils.isNotEmpty(likes)) {
			errors.add(GLOBAL_MESSAGE, new ActionMessage("既に いいね！をしています。", false));
		}
		return errors;
	}

	/**
	 * 更新処理をする前に検証を行います。
	 * 
	 * @return
	 */
	public ActionMessages validateBeforeUpdate() {
		ActionMessages errors = new ActionMessages();

		Room room = roomService.findById(toLong(form.roomId));
		if (room == null) {
			throw new RuntimeException("ルームが見つかりません。id:" + form.roomId);
		}
		return errors;
	}
}