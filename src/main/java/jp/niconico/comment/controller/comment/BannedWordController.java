package jp.niconico.comment.controller.comment;

import java.util.regex.Pattern;

import jp.niconico.comment.model.entity.comment.BannedWord;
import jp.niconico.comment.repository.BannedWordService;
import org.springframework.beans.factory.annotation.Autowired;

public class BannedWordController {
	@Autowired
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
