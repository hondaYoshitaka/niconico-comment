package jp.niconico.comment.service;

import static jp.niconico.comment.entity.names.S2sessionNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import jp.niconico.comment.entity.S2session;

/**
 * {@link S2session}のサービスクラスです。
 * 
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2014/02/18 15:04:06")
public class S2sessionService extends AbstractService<S2session> {
	
	/**
	 * 識別子でエンティティを検索します。
	 * 
	 * @param sessionId
	 *            識別子
	 * @param name
	 *            識別子
	 * @return エンティティ
	 */
	public S2session findById(String sessionId, String name) {
		return select().id(sessionId, name).getSingleResult();
	}
	
	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 * 
	 * @return エンティティのリスト
	 */
	public List<S2session> findAllOrderById() {
		return select().orderBy(asc(sessionId()), asc(name())).getResultList();
	}
	
	/**
	 * 有効期限切れのsessionを削除します。
	 * 
	 * @param expiredDatetime
	 * @return
	 */
	public int deleteAllExpired(Timestamp currentDatetime, Long maxAge) {
		Map<String, String> param = new HashMap<String, String>();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Timestamp expired = new Timestamp(currentDatetime.getTime() - maxAge);
		param.put("expiredDatetime", df.format(expired));
		
		return updateBySqlFile("deleteAllExpired.sql", param).execute();
	}
}