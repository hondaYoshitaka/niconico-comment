package jp.niconico.comment.exception.ana;

import jp.niconico.comment.dto.LoginDto;
import jp.niconico.comment.enums.Permissions;

import org.seasar.framework.beans.util.Beans;

/**
 * 認可されていない(権限が足りていない)ことを表す
 */
public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final LoginDto dto;

	private final Permissions requiredPermission;

	/**
	 * 認可されなかったログインユーザー情報と、必要とした権限を引数に持つコンストラクタ
	 * 
	 * @param dto
	 * @param requiredPermission
	 */
	public NotAuthorizedException(LoginDto dto, Permissions requiredPermission) {
		super(String.format("[role(%s)] insufficient permission. [%s]", "unknown", requiredPermission));
		this.dto = Beans.createAndCopy(LoginDto.class, dto).execute();
		this.requiredPermission = requiredPermission;
	}

	/**
	 * 認可されなかったログインユーザー情報を取得します。
	 * 
	 * @return 認可されなかったログインユーザー情報
	 */
	public LoginDto getUserLoginDto() {
		return this.dto;
	}

	/**
	 * 必要とした権限を取得します。
	 * 
	 * @return 必要とした権限
	 */
	public Permissions getRequiredPermission() {
		return this.requiredPermission;
	}
}
