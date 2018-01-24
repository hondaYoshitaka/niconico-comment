package jp.niconico.comment.repository;

import java.util.List;
import javax.annotation.Generated;
import jp.niconico.comment.entity.LikeRoom;

import static jp.niconico.comment.entity.names.LikeRoomNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link LikeRoom}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.47", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2015/02/24 19:04:08")
public class LikeRoomService extends AbstractService<LikeRoom> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param likeRoomId
     *            識別子
     * @return エンティティ
     */
    public LikeRoom findById(Long likeRoomId) {
        return select().id(likeRoomId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<LikeRoom> findAllOrderById() {
        return select().orderBy(asc(likeRoomId())).getResultList();
    }
}