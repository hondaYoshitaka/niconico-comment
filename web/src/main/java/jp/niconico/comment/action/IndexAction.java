package jp.niconico.comment.action;

import java.util.List;

import javax.annotation.Resource;

import jp.niconico.comment.annotation.ana.Allow;
import jp.niconico.comment.dto.LoginDto;
import jp.niconico.comment.entity.Room;
import jp.niconico.comment.enums.Permissions;
import jp.niconico.comment.service.RoomService;

import org.seasar.struts.annotation.Execute;

public class IndexAction {

	@Resource
	public LoginDto loginDto;

	@Resource
	protected RoomService roomService;

	public List<Room> rooms;

	@Allow(permission = Permissions.LOGIN)
	@Execute(validator = false)
	public String index() {
		rooms = roomService.findAllOrderById();

		for (Room room : rooms) {
			System.out.println("文字化けテスト: " + room.roomName);
		}
		return "index.jsp";
	}
}
