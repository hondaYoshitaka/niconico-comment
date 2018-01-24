package jp.niconico.comment.model.form;

import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Required;

public class RoomForm {

	@Required(target = "show,update")
	public String roomId;

	@Required(target = "create, update")
	@Maxlength(target = "create,update", maxlength = 100)
	public String roomName;

	@Maxlength(target = "create,update", maxlength = 255)
	public String detail;

}
