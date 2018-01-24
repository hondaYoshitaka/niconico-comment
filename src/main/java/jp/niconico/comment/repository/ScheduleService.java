package jp.niconico.comment.repository;

import static jp.niconico.comment.entity.names.ScheduleNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.niconico.comment.entity.Schedule;

/**
 * {@link Schedule}のサービスクラスです。
 * 
 */
@Generated(value = { "S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2014/02/12 19:07:14")
public class ScheduleService extends AbstractService<Schedule> {

	/**
	 * 識別子でエンティティを検索します。
	 * 
	 * @param scheduleId
	 *            識別子
	 * @return エンティティ
	 */
	public Schedule findById(Long scheduleId) {
		return select().id(scheduleId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 * 
	 * @return エンティティのリスト
	 */
	public List<Schedule> findAllOrderById() {
		return select().orderBy(asc(scheduleId())).getResultList();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 * 
	 * @return エンティティのリスト
	 */
	public List<Schedule> findAllOrderByStartDate(Long roomId) {
		return select()
				.where(eq(roomId(), roomId))
				.orderBy(asc(startDatetime()))
				.getResultList();
	}
}