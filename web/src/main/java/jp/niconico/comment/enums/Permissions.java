package jp.niconico.comment.enums;

/**
 * 権限のコードを定義します。
 */
public enum Permissions {
	/** ログイン */
	LOGIN("ログイン"), ADMIN("管理者");

	private String label;

	private Permissions(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}