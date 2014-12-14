package jp.niconico.comment.entity.names;

import javax.annotation.Generated;
import jp.niconico.comment.entity.BannedWord;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BannedWord}のプロパティ名の集合です。
 * 
 */
@Generated("GSP")
public class BannedWordNames {

    /**
     * bannedWordIdのプロパティ名を返します。
     * 
     * @return bannedWordIdのプロパティ名
     */
    public static PropertyName<Long> bannedWordId() {
        return new PropertyName<Long>("bannedWordId");
    }

    /**
     * wordのプロパティ名を返します。
     * 
     * @return wordのプロパティ名
     */
    public static PropertyName<String> word() {
        return new PropertyName<String>("word");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _BannedWordNames extends PropertyName<BannedWord> {

        /**
         * インスタンスを構築します。
         */
        public _BannedWordNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BannedWordNames(final String name) {
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
        public _BannedWordNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * bannedWordIdのプロパティ名を返します。
         *
         * @return bannedWordIdのプロパティ名
         */
        public PropertyName<Long> bannedWordId() {
            return new PropertyName<Long>(this, "bannedWordId");
        }

        /**
         * wordのプロパティ名を返します。
         *
         * @return wordのプロパティ名
         */
        public PropertyName<String> word() {
            return new PropertyName<String>(this, "word");
        }
    }
}
