package jp.niconico.comment.model.form;

import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Required;

public class LoginForm {

	@Required(target = "login", arg0 = @Arg(key = "labels.login.userName"))
	public String userName;

	@Required(target = "login", arg0 = @Arg(key = "labels.login.password"))
	public String password;
}
