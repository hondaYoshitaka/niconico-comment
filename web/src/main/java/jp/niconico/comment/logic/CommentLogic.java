package jp.niconico.comment.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import jp.niconico.comment.service.CommentService;

import org.apache.commons.lang.StringUtils;

public class CommentLogic {

	@Resource
	protected CommentService commentService;

	@Resource
	protected Pattern bannedWordPattern;

	/**
	 * 禁止用語をマスクします。
	 * 
	 * @param comment
	 * @return マスクしたコメント
	 */
	public String maskBannedWord(String comment) {
		Matcher m = bannedWordPattern.matcher(comment);
		return m.replaceAll("※※※");
	}

	/**
	 * 最新のコメントIDの番号を返します。
	 * 
	 * @return 最新のコメントID
	 */
	public String findLatestCommentId(Long roomId) {
		String latestCommentId = commentService.getLatestCommentId(roomId);
		if (StringUtils.isEmpty(latestCommentId)) {
			latestCommentId = "0";
		}
		return latestCommentId;
	}

}
