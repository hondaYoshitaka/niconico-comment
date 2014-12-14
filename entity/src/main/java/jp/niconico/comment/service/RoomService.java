package jp.niconico.comment.service;

import java.util.List;
import javax.annotation.Generated;
import jp.niconico.comment.entity.Room;

import static jp.niconico.comment.entity.names.RoomNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Room}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/03/10 13:57:32")
public class RoomService extends AbstractService<Room> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param roomId
     *            識別子
     * @return エンティティ
     */
    public Room findById(Long roomId) {
        return select()
        		.id(roomId)
        		.getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Room> findAllOrderById() {
        return select()
        		.orderBy(desc(roomId()))
        		.getResultList();
    }
}