package jp.niconico.comment.action;

import java.util.regex.Pattern;

import javax.annotation.Resource;

import jp.niconico.comment.entity.BannedWord;
import jp.niconico.comment.service.BannedWordService;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class BannedWordAction {
	@Resource
	protected BannedWordService bannedWordService;

	/**
	 * <b>server起動時</b>に禁止用語を読み込んでおくためのメソッド<br/>
	 * コメント取得時に頻繁にアクセスされるので、予めロードしておいて高速化をはかる
	 */
	public void load() {
		StringBuilder reg = new StringBuilder("");
		for (BannedWord banned : bannedWordService.findAll()) {
			if (reg.length() != 0) {
				reg.append("|");
			}
			reg.append(banned.word);
		}
		Pattern bannedWordPattern = Pattern.compile(reg.toString());

		S2Container container = SingletonS2ContainerFactory.getContainer();
		container.register(bannedWordPattern, "bannedWordPattern");
	}
}
