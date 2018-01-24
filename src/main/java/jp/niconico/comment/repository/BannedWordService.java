package jp.niconico.comment.repository;

import java.util.List;
import javax.annotation.Generated;
import jp.niconico.comment.entity.BannedWord;

import static jp.niconico.comment.entity.names.BannedWordNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link BannedWord}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/02/13 19:45:11")
public class BannedWordService extends AbstractService<BannedWord> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param bannedWordId
     *            識別子
     * @return エンティティ
     */
    public BannedWord findById(Long bannedWordId) {
        return select().id(bannedWordId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<BannedWord> findAllOrderById() {
        return select().orderBy(asc(bannedWordId())).getResultList();
    }
}