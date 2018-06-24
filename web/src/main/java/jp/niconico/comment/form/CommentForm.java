package jp.niconico.comment.form;

import org.seasar.struts.annotation.Required;

public class CommentForm {

	public String comment;

	@Required(target = "destroy")
	public String commentId;

	@Required(target = "findComment,countChart")
	public String scheduleId;

	@Required(target = "countChart")
	public String eachSecond;

	@Required(target = "create,search,list,input,statistical")
	public String roomId;
}
