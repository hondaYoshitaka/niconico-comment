package jp.niconico.comment.entity.names;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.niconico.comment.entity.S2session;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link S2session}のプロパティ名の集合です。
 * 
 */
@Generated("GSP")
public class S2sessionNames {

    /**
     * sessionIdのプロパティ名を返します。
     * 
     * @return sessionIdのプロパティ名
     */
    public static PropertyName<String> sessionId() {
        return new PropertyName<String>("sessionId");
    }

    /**
     * nameのプロパティ名を返します。
     * 
     * @return nameのプロパティ名
     */
    public static PropertyName<String> name() {
        return new PropertyName<String>("name");
    }

    /**
     * valueのプロパティ名を返します。
     * 
     * @return valueのプロパティ名
     */
    public static PropertyName<byte[]> value() {
        return new PropertyName<byte[]>("value");
    }

    /**
     * lastAccessのプロパティ名を返します。
     * 
     * @return lastAccessのプロパティ名
     */
    public static PropertyName<Timestamp> lastAccess() {
        return new PropertyName<Timestamp>("lastAccess");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _S2sessionNames extends PropertyName<S2session> {

        /**
         * インスタンスを構築します。
         */
        public _S2sessionNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _S2sessionNames(final String name) {
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
        public _S2sessionNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * sessionIdのプロパティ名を返します。
         *
         * @return sessionIdのプロパティ名
         */
        public PropertyName<String> sessionId() {
            return new PropertyName<String>(this, "sessionId");
        }

        /**
         * nameのプロパティ名を返します。
         *
         * @return nameのプロパティ名
         */
        public PropertyName<String> name() {
            return new PropertyName<String>(this, "name");
        }

        /**
         * valueのプロパティ名を返します。
         *
         * @return valueのプロパティ名
         */
        public PropertyName<byte[]> value() {
            return new PropertyName<byte[]>(this, "value");
        }

        /**
         * lastAccessのプロパティ名を返します。
         *
         * @return lastAccessのプロパティ名
         */
        public PropertyName<Timestamp> lastAccess() {
            return new PropertyName<Timestamp>(this, "lastAccess");
        }
    }
}
