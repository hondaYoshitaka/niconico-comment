package jp.niconico.comment.entity.names;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.niconico.comment.entity.Comment;
import jp.niconico.comment.entity.names.RoomNames._RoomNames;
import jp.niconico.comment.entity.names.UserNames._UserNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Comment}のプロパティ名の集合です。
 * 
 */
@Generated("GSP")
public class CommentNames {

    /**
     * commentIdのプロパティ名を返します。
     * 
     * @return commentIdのプロパティ名
     */
    public static PropertyName<Long> commentId() {
        return new PropertyName<Long>("commentId");
    }

    /**
     * commentのプロパティ名を返します。
     * 
     * @return commentのプロパティ名
     */
    public static PropertyName<String> comment() {
        return new PropertyName<String>("comment");
    }

    /**
     * commentDatetimeのプロパティ名を返します。
     * 
     * @return commentDatetimeのプロパティ名
     */
    public static PropertyName<Timestamp> commentDatetime() {
        return new PropertyName<Timestamp>("commentDatetime");
    }

    /**
     * userIdのプロパティ名を返します。
     * 
     * @return userIdのプロパティ名
     */
    public static PropertyName<Long> userId() {
        return new PropertyName<Long>("userId");
    }

    /**
     * roomIdのプロパティ名を返します。
     * 
     * @return roomIdのプロパティ名
     */
    public static PropertyName<Long> roomId() {
        return new PropertyName<Long>("roomId");
    }

    /**
     * userのプロパティ名を返します。
     * 
     * @return userのプロパティ名
     */
    public static _UserNames user() {
        return new _UserNames("user");
    }

    /**
     * roomのプロパティ名を返します。
     * 
     * @return roomのプロパティ名
     */
    public static _RoomNames room() {
        return new _RoomNames("room");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _CommentNames extends PropertyName<Comment> {

        /**
         * インスタンスを構築します。
         */
        public _CommentNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CommentNames(final String name) {
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
        public _CommentNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * commentIdのプロパティ名を返します。
         *
         * @return commentIdのプロパティ名
         */
        public PropertyName<Long> commentId() {
            return new PropertyName<Long>(this, "commentId");
        }

        /**
         * commentのプロパティ名を返します。
         *
         * @return commentのプロパティ名
         */
        public PropertyName<String> comment() {
            return new PropertyName<String>(this, "comment");
        }

        /**
         * commentDatetimeのプロパティ名を返します。
         *
         * @return commentDatetimeのプロパティ名
         */
        public PropertyName<Timestamp> commentDatetime() {
            return new PropertyName<Timestamp>(this, "commentDatetime");
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
         * roomIdのプロパティ名を返します。
         *
         * @return roomIdのプロパティ名
         */
        public PropertyName<Long> roomId() {
            return new PropertyName<Long>(this, "roomId");
        }

        /**
         * userのプロパティ名を返します。
         * 
         * @return userのプロパティ名
         */
        public _UserNames user() {
            return new _UserNames(this, "user");
        }

        /**
         * roomのプロパティ名を返します。
         * 
         * @return roomのプロパティ名
         */
        public _RoomNames room() {
            return new _RoomNames(this, "room");
        }
    }
}
