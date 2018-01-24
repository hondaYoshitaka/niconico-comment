package jp.niconico.comment.model.form.comment;

import lombok.Data;
import org.seasar.struts.annotation.Required;

public class CommentForm {

	public String comment;

	public String commentId;

	@Required(target = "findComment,countChart")
	public String scheduleId;

	@Required(target = "countChart")
	public String eachSecond;

	@Required(target = "create,search,list,input,statistical")
	public String roomId;
}
