package jp.niconico.comment.action;

import jp.niconico.comment.dto.LoginDto;
import jp.niconico.comment.entity.User;
import jp.niconico.comment.enums.Permissions;
import jp.niconico.comment.form.LoginForm;
import jp.niconico.comment.logic.PasswordLogic;
import jp.niconico.comment.service.UserService;
import jp.niconico.comment.util.DateUtil;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.tiger.CollectionsUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import javax.annotation.Resource;
import java.sql.Timestamp;

import static jp.niconico.comment.util.UrlRewriterUtil.urlFor;
import static org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE;

public class LoginAction {
    @ActionForm
    @Resource(name = "loginForm")
    public LoginForm form;

    @Resource
    public LoginDto loginDto;

    protected User user;

    @Resource
    protected UserService userService;

    @Execute(validator = false)
    public String index() {
        return "input";
    }

    @Execute(validator = false)
    public String input() {
        return "input.jsp";
    }

    @Execute(validate = "validateLogin", input = "input", redirect = true)
    public String login() {
        final User user = userService.findByName(form.userName);

        Beans.copy(user, loginDto).execute();

        // grant Permissions
        loginDto.permissions = CollectionsUtil.newHashSet();
        loginDto.permissions.add(Permissions.LOGIN);
        if (user.isAdmin) {
            loginDto.permissions.add(Permissions.ADMIN);
        }
        // FIXME session固定化攻撃対策でsessionIdの履き替えを行う

        return urlFor("Index", "index");
    }

    @RemoveSession(name = "loginDto")
    @Execute(validator = false, redirect = true)
    public String logout() {

        return urlFor("Login", "index");
    }

    public ActionMessages validateLogin() {
        final ActionMessages errors = new ActionMessages();

        final User user = userService.findByName(form.userName);
        if (user == null) {
            errors.add(GLOBAL_MESSAGE, new ActionMessage("errors.login"));
            return errors;
        }

        boolean isCorrectPassword = PasswordLogic.isCorrectPassword(user.password, form.password, user.salt);
        if (!isCorrectPassword) {
            errors.add(GLOBAL_MESSAGE, new ActionMessage("errors.login"));
            return errors;
        }

        final Timestamp currentTimestamp = DateUtil.getCurrentTimestamp();
        final boolean isExpired = user.expiredAt.before(currentTimestamp);
        if (isExpired) {
            errors.add(GLOBAL_MESSAGE, new ActionMessage("errors.login"));
            return errors;
        }

        return errors;
    }
}
