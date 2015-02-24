package jp.niconico.comment.entity.names;

import javax.annotation.Generated;
import jp.niconico.comment.entity.Room;
import jp.niconico.comment.entity.names.CommentNames._CommentNames;
import jp.niconico.comment.entity.names.LikeRoomNames._LikeRoomNames;
import jp.niconico.comment.entity.names.ScheduleNames._ScheduleNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Room}のプロパティ名の集合です。
 * 
 */
@Generated("GSP")
public class RoomNames {

    /**
     * roomIdのプロパティ名を返します。
     * 
     * @return roomIdのプロパティ名
     */
    public static PropertyName<Long> roomId() {
        return new PropertyName<Long>("roomId");
    }

    /**
     * roomNameのプロパティ名を返します。
     * 
     * @return roomNameのプロパティ名
     */
    public static PropertyName<String> roomName() {
        return new PropertyName<String>("roomName");
    }

    /**
     * detailのプロパティ名を返します。
     * 
     * @return detailのプロパティ名
     */
    public static PropertyName<String> detail() {
        return new PropertyName<String>("detail");
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
     * likeRoomListのプロパティ名を返します。
     * 
     * @return likeRoomListのプロパティ名
     */
    public static _LikeRoomNames likeRoomList() {
        return new _LikeRoomNames("likeRoomList");
    }

    /**
     * scheduleListのプロパティ名を返します。
     * 
     * @return scheduleListのプロパティ名
     */
    public static _ScheduleNames scheduleList() {
        return new _ScheduleNames("scheduleList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _RoomNames extends PropertyName<Room> {

        /**
         * インスタンスを構築します。
         */
        public _RoomNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _RoomNames(final String name) {
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
        public _RoomNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * roomNameのプロパティ名を返します。
         *
         * @return roomNameのプロパティ名
         */
        public PropertyName<String> roomName() {
            return new PropertyName<String>(this, "roomName");
        }

        /**
         * detailのプロパティ名を返します。
         *
         * @return detailのプロパティ名
         */
        public PropertyName<String> detail() {
            return new PropertyName<String>(this, "detail");
        }

        /**
         * commentListのプロパティ名を返します。
         * 
         * @return commentListのプロパティ名
         */
        public _CommentNames commentList() {
            return new _CommentNames(this, "commentList");
        }

        /**
         * likeRoomListのプロパティ名を返します。
         * 
         * @return likeRoomListのプロパティ名
         */
        public _LikeRoomNames likeRoomList() {
            return new _LikeRoomNames(this, "likeRoomList");
        }

        /**
         * scheduleListのプロパティ名を返します。
         * 
         * @return scheduleListのプロパティ名
         */
        public _ScheduleNames scheduleList() {
            return new _ScheduleNames(this, "scheduleList");
        }
    }
}
