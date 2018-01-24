package jp.niconico.comment.entity.names;

import javax.annotation.Generated;
import jp.niconico.comment.entity.User;
import jp.niconico.comment.entity.names.CommentNames._CommentNames;
import org.seasar.extension.jdbc.name.PropertyName;

import java.sql.Timestamp;

/**
 * {@link User}のプロパティ名の集合です。
 * 
 */
@Generated("GSP")
public class UserNames {

    /**
     * userIdのプロパティ名を返します。
     * 
     * @return userIdのプロパティ名
     */
    public static PropertyName<Long> userId() {
        return new PropertyName<Long>("userId");
    }

    /**
     * userNameのプロパティ名を返します。
     * 
     * @return userNameのプロパティ名
     */
    public static PropertyName<String> userName() {
        return new PropertyName<String>("userName");
    }

    /**
     * passwordのプロパティ名を返します。
     * 
     * @return passwordのプロパティ名
     */
    public static PropertyName<String> password() {
        return new PropertyName<String>("password");
    }

    /**
     * saltのプロパティ名を返します。
     * 
     * @return saltのプロパティ名
     */
    public static PropertyName<String> salt() {
        return new PropertyName<String>("salt");
    }

    /**
     * isAdminのプロパティ名を返します。
     * 
     * @return isAdminのプロパティ名
     */
    public static PropertyName<Boolean> isAdmin() {
        return new PropertyName<Boolean>("isAdmin");
    }

    public static PropertyName<Timestamp> expiredAt() {
        return new PropertyName<Timestamp>("expiredAt");
    }


    /**
     * commentListのプロパティ名を返します。
     * 
     * @return commentListのプロパティ名
     */
    public static _CommentNames commentList() {
        return new _CommentNames("commentList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _UserNames extends PropertyName<User> {

        /**
         * インスタンスを構築します。
         */
        public _UserNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _UserNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _UserNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * userIdのプロパティ名を返します。
         *
         * @return userIdのプロパティ名
         */
        public PropertyName<Long> userId() {
            return new PropertyName<Long>(this, "userId");
        }

        /**
         * userNameのプロパティ名を返します。
         *
         * @return userNameのプロパティ名
         */
        public PropertyName<String> userName() {
            return new PropertyName<String>(this, "userName");
        }

        /**
         * passwordのプロパティ名を返します。
         *
         * @return passwordのプロパティ名
         */
        public PropertyName<String> password() {
            return new PropertyName<String>(this, "password");
        }

        /**
         * saltのプロパティ名を返します。
         *
         * @return saltのプロパティ名
         */
        public PropertyName<String> salt() {
            return new PropertyName<String>(this, "salt");
        }

        /**
         * isAdminのプロパティ名を返します。
         *
         * @return isAdminのプロパティ名
         */
        public PropertyName<Boolean> isAdmin() {
            return new PropertyName<Boolean>(this, "isAdmin");
        }

        /**
         * commentListのプロパティ名を返します。
         * 
         * @return commentListのプロパティ名
         */
        public _CommentNames commentList() {
            return new _CommentNames(this, "commentList");
        }
    }
}
