package jp.niconico.comment.entity.names;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.niconico.comment.entity.LikeRoom;
import jp.niconico.comment.entity.names.RoomNames._RoomNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link LikeRoom}のプロパティ名の集合です。
 * 
 */
@Generated("GSP")
public class LikeRoomNames {

    /**
     * likeRoomIdのプロパティ名を返します。
     * 
     * @return likeRoomIdのプロパティ名
     */
    public static PropertyName<Long> likeRoomId() {
        return new PropertyName<Long>("likeRoomId");
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
     * likedByのプロパティ名を返します。
     * 
     * @return likedByのプロパティ名
     */
    public static PropertyName<String> likedBy() {
        return new PropertyName<String>("likedBy");
    }

    /**
     * likedDatetimeのプロパティ名を返します。
     * 
     * @return likedDatetimeのプロパティ名
     */
    public static PropertyName<Timestamp> likedDatetime() {
        return new PropertyName<Timestamp>("likedDatetime");
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
    public static class _LikeRoomNames extends PropertyName<LikeRoom> {

        /**
         * インスタンスを構築します。
         */
        public _LikeRoomNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _LikeRoomNames(final String name) {
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
        public _LikeRoomNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * likeRoomIdのプロパティ名を返します。
         *
         * @return likeRoomIdのプロパティ名
         */
        public PropertyName<Long> likeRoomId() {
            return new PropertyName<Long>(this, "likeRoomId");
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
         * likedByのプロパティ名を返します。
         *
         * @return likedByのプロパティ名
         */
        public PropertyName<String> likedBy() {
            return new PropertyName<String>(this, "likedBy");
        }

        /**
         * likedDatetimeのプロパティ名を返します。
         *
         * @return likedDatetimeのプロパティ名
         */
        public PropertyName<Timestamp> likedDatetime() {
            return new PropertyName<Timestamp>(this, "likedDatetime");
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
