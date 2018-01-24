package jp.niconico.comment.repository;

import static jp.niconico.comment.entity.names.UserNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.niconico.comment.entity.User;

/**
 * {@link User}のサービスクラスです。
 * 
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2014/02/12 19:07:14")
public class UserService extends AbstractService<User> {

	/**
	 * 識別子でエンティティを検索します。
	 * 
	 * @param userId
	 *            識別子
	 * @return エンティティ
	 */
	public User findById(Long userId) {
		return select().id(userId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 * 
	 * @return エンティティのリスト
	 */
	public List<User> findAllOrderById() {
		return select().orderBy(asc(userId())).getResultList();
	}

	public User findByName(String userName) {
		return select().where(eq(userName(), userName)).getSingleResult();
	}

	public User findByNameAndPass(String userName, String password) {
		return select().where(and(eq(userName(), userName), eq(password(), password))).getSingleResult();
	}
}