package jp.niconico.comment.model.entity.room;

import jp.niconico.comment.model.entity.comment.Comment;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 *
 */
@Generated("GSP")
@Entity
@Table(name = "room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ROOM_ID */
    @Id
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    @Column(precision = 19, nullable = false, unique = true)
    public Long roomId;

    /** ROOM_NAME */
    @Column(length = 100, nullable = false, unique = false)
    public String roomName;

    /** DETAIL */
    @Column(length = 255, nullable = true, unique = false)
    public String detail;

    /** commentList関連プロパティ */
    @OneToMany(mappedBy = "room")
    public List<Comment> commentList;

    /** likeRoomList関連プロパティ */
    @OneToMany(mappedBy = "room")
    public List<LikeRoom> likeRoomList;

    /** scheduleList関連プロパティ */
    @OneToMany(mappedBy = "room")
    public List<Schedule> scheduleList;
}
