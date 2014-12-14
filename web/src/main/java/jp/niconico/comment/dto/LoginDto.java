package jp.niconico.comment.dto;

import java.io.Serializable;
import java.util.Set;

import jp.niconico.comment.enums.Permissions;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.framework.util.tiger.CollectionsUtil;

@Component(instance = InstanceType.SESSION, name = "loginDto")
public class LoginDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public Long userId;

	public String userName;

	public Set<Permissions> permissions = CollectionsUtil.newHashSet();

	/**
	 * admin権限を持つか
	 * 
	 * @return
	 */
	public boolean getIsAdmin() {
		return this.permissions.contains(Permissions.ADMIN);
	}
}
