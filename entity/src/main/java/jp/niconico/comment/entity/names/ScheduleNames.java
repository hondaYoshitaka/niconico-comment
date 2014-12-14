package jp.niconico.comment.entity.names;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.niconico.comment.entity.Schedule;
import jp.niconico.comment.entity.names.RoomNames._RoomNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Schedule}のプロパティ名の集合です。
 * 
 */
@Generated("GSP")
public class ScheduleNames {

    /**
     * scheduleIdのプロパティ名を返します。
     * 
     * @return scheduleIdのプロパティ名
     */
    public static PropertyName<Long> scheduleId() {
        return new PropertyName<Long>("scheduleId");
    }

    /**
     * scheduleNameのプロパティ名を返します。
     * 
     * @return scheduleNameのプロパティ名
     */
    public static PropertyName<String> scheduleName() {
        return new PropertyName<String>("scheduleName");
    }

    /**
     * startDatetimeのプロパティ名を返します。
     * 
     * @return startDatetimeのプロパティ名
     */
    public static PropertyName<Timestamp> startDatetime() {
        return new PropertyName<Timestamp>("startDatetime");
    }

    /**
     * endDatetimeのプロパティ名を返します。
     * 
     * @return endDatetimeのプロパティ名
     */
    public static PropertyName<Timestamp> endDatetime() {
        return new PropertyName<Timestamp>("endDatetime");
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
     * roomIdのプロパティ名を返します。
     * 
     * @return roomIdのプロパティ名
     */
    public static PropertyName<Long> roomId() {
        return new PropertyName<Long>("roomId");
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
    public static class _ScheduleNames extends PropertyName<Schedule> {

        /**
         * インスタンスを構築します。
         */
        public _ScheduleNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _ScheduleNames(final String name) {
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
        public _ScheduleNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * scheduleIdのプロパティ名を返します。
         *
         * @return scheduleIdのプロパティ名
         */
        public PropertyName<Long> scheduleId() {
            return new PropertyName<Long>(this, "scheduleId");
        }

        /**
         * scheduleNameのプロパティ名を返します。
         *
         * @return scheduleNameのプロパティ名
         */
        public PropertyName<String> scheduleName() {
            return new PropertyName<String>(this, "scheduleName");
        }

        /**
         * startDatetimeのプロパティ名を返します。
         *
         * @return startDatetimeのプロパティ名
         */
        public PropertyName<Timestamp> startDatetime() {
            return new PropertyName<Timestamp>(this, "startDatetime");
        }

        /**
         * endDatetimeのプロパティ名を返します。
         *
         * @return endDatetimeのプロパティ名
         */
        public PropertyName<Timestamp> endDatetime() {
            return new PropertyName<Timestamp>(this, "endDatetime");
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
         * roomIdのプロパティ名を返します。
         *
         * @return roomIdのプロパティ名
         */
        public PropertyName<Long> roomId() {
            return new PropertyName<Long>(this, "roomId");
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
