package jp.niconico.comment.util.ana;

import jp.niconico.comment.dto.LoginDto;
import jp.niconico.comment.enums.Permissions;

/**
 * 認証と認可に関するユーティリティ
 */
public final class AnAUtil {
	/**
	 * アクセスチェックの結果
	 */
	public static enum AccessCheck {
		/** 認証・権限ともに問題無し */
		Success,
		/** 認証されていない */
		NotAuthenticated,
		/** 権限が足りていない */
		NotAuthorized
	}

	private AnAUtil() {
	}

	/**
	 * 指定の許可権限を持っているかチェックします
	 * 
	 * @param dto
	 *            ユーザーのログイン状態
	 * @param permission
	 *            許可権限
	 * @return ログインしていない場合は{@link AccessCheck#NotAuthenticated}, 許可権限を持っている場合は
	 *         {@link AccessCheck#Success}, 許可権限を持っていない場合は
	 *         {@link AccessCheck#NotAuthorized}
	 */
	public static AccessCheck allow(LoginDto dto, Permissions permission) {
		return authorization(dto, permission, null);
	}

	/**
	 * 指定の拒否権限を持っているかチェックします。
	 * 
	 * @param dto
	 *            ユーザーのログイン状態
	 * @param permission
	 *            拒否権限
	 * @return ログインしていない場合は{@link AccessCheck#NotAuthenticated}, 拒否権限を持っている場合は
	 *         {@link AccessCheck#NotAuthorized}, 拒否権限を持っていない場合は
	 *         {@link AccessCheck#Success}
	 */
	public static AccessCheck deny(LoginDto dto, Permissions permission) {
		return authorization(dto, null, permission);
	}

	/**
	 * 権限チェックを行います。
	 * 
	 * @param dto
	 *            ユーザーのログイン状態
	 * @param allow
	 *            許可する権限
	 * @param deny
	 *            拒否する権限
	 * @return ログインしていない場合は{@link AccessCheck#NotAuthenticated},
	 *         拒否権限を持っている、もしくは許可権限を持っていない場合は{@link AccessCheck#NotAuthorized},
	 *         拒否権限を持ってなくて、かつ許可権限を持っている場合は{@link AccessCheck#Success},
	 *         拒否権限を持ってなくて、かつ許可権限も持っていない場合は{@link AccessCheck#NotAuthorized}
	 */
	public static AccessCheck authorization(LoginDto dto, Permissions allow, Permissions deny) {
		if (allow == null && deny == null) {
			throw new IllegalArgumentException("allow or deny element is required.");
		}
		if (dto == null || dto.userId == null) {
			return AccessCheck.NotAuthenticated;
		}
		if (deny != null && dto.permissions.contains(deny)) {
			return AccessCheck.NotAuthorized;
		}
		if (allow == null || (allow != null && dto.permissions.contains(allow))) {
			return AccessCheck.Success;
		}
		return AccessCheck.NotAuthorized;
	}
}
