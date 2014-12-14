package jp.niconico.comment.entity.names;

import javax.annotation.Generated;
import jp.niconico.comment.entity.BannedWord;
import jp.niconico.comment.entity.Comment;
import jp.niconico.comment.entity.Room;
import jp.niconico.comment.entity.S2session;
import jp.niconico.comment.entity.Schedule;
import jp.niconico.comment.entity.User;
import jp.niconico.comment.entity.names.BannedWordNames._BannedWordNames;
import jp.niconico.comment.entity.names.CommentNames._CommentNames;
import jp.niconico.comment.entity.names.RoomNames._RoomNames;
import jp.niconico.comment.entity.names.S2sessionNames._S2sessionNames;
import jp.niconico.comment.entity.names.ScheduleNames._ScheduleNames;
import jp.niconico.comment.entity.names.UserNames._UserNames;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated("GSP")
public class Names {

    /**
     * {@link BannedWord}の名前クラスを返します。
     * 
     * @return BannedWordの名前クラス
     */
    public static _BannedWordNames bannedWord() {
        return new _BannedWordNames();
    }

    /**
     * {@link Comment}の名前クラスを返します。
     * 
     * @return Commentの名前クラス
     */
    public static _CommentNames comment() {
        return new _CommentNames();
    }

    /**
     * {@link Room}の名前クラスを返します。
     * 
     * @return Roomの名前クラス
     */
    public static _RoomNames room() {
        return new _RoomNames();
    }

    /**
     * {@link S2session}の名前クラスを返します。
     * 
     * @return S2sessionの名前クラス
     */
    public static _S2sessionNames s2session() {
        return new _S2sessionNames();
    }

    /**
     * {@link Schedule}の名前クラスを返します。
     * 
     * @return Scheduleの名前クラス
     */
    public static _ScheduleNames schedule() {
        return new _ScheduleNames();
    }

    /**
     * {@link User}の名前クラスを返します。
     * 
     * @return Userの名前クラス
     */
    public static _UserNames user() {
        return new _UserNames();
    }
}
