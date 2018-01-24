package jp.niconico.comment.repository;

import static jp.niconico.comment.entity.names.CommentNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import jp.niconico.comment.entity.Comment;

import org.seasar.extension.jdbc.IterationCallback;
import org.seasar.framework.beans.util.BeanMap;

/**
 * {@link Comment}のサービスクラスです。
 * 
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2014/02/12 19:07:14")
public class CommentService extends AbstractService<Comment> {

	/**
	 * 識別子でエンティティを検索します。
	 * 
	 * @param commentId
	 *            識別子
	 * @return エンティティ
	 */
	public Comment findById(Long commentId) {
		return select().id(commentId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 * 
	 * @return エンティティのリスト
	 */
	public List<Comment> findAllOrderById() {
		return select().orderBy(asc(commentId())).getResultList();
	}

	/**
	 * room内のすべてのコメントを検索します。
	 * 
	 * @return
	 */
	public List<Comment> findAllInRoom(Long roomId) {
		return select().where(eq(roomId(), roomId)).orderBy(asc(commentId())).getResultList();
	}

	/**
	 * room内のすべてのコメントを検索します。
	 * 
	 * @return
	 */
	public int findAllInRoom(Long roomId, IterationCallback<Comment, Integer> callback) {
		return select()
				.where(eq(roomId(), roomId))
				.orderBy(asc(commentId()))
				.iterate(callback);
	}

	/**
	 * room内の引数のcommentIdより最新のコメントを検索します。
	 * 
	 * @param roomId
	 * @param commentId
	 * @return
	 */
	public List<Comment> findGreaterThanIdInRoom(Long roomId, Long commentId) {
		return select().where(and(eq(roomId(), roomId), gt(commentId(), commentId))).orderBy(asc(commentId()))
				.getResultList();
	}

	/**
	 * room内の最新のコメントIDを返します。
	 * 
	 * @return
	 */
	public String getLatestCommentId(Long roomId) {
		BeanMap params = new BeanMap();
		params.put("roomId", roomId);
		return selectBySqlFile(String.class, "getLatestCommentId.sql", params).getSingleResult();
	}

	public List<Comment> findByDate(Long roomId, Timestamp from, Timestamp to) {
		return select().where(and(ge(commentDatetime(), from), lt(commentDatetime(), to), eq(roomId(), roomId)))
				.getResultList();

	}

	/**
	 * 指定された日付の範囲にあるコメントの投稿の数を取得します。
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public long getCount(Long roomId, Timestamp from, Timestamp to) {
		return select().where(and(ge(commentDatetime(), from), lt(commentDatetime(), to), eq(roomId(), roomId)))
				.getCount();
	}
}