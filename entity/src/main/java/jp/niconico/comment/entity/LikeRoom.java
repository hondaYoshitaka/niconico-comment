package jp.niconico.comment.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 *
 */
@Generated("GSP")
@Entity
@Table(name = "like_room")
public class LikeRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    /** LIKE_ROOM_ID */
    @Id
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    @Column(precision = 19, nullable = false, unique = true)
    public Long likeRoomId;

    /** ROOM_ID */
    @Column(precision = 19, nullable = false, unique = false)
    public Long roomId;

    /** LIKED_BY */
    @Column(length = 255, nullable = false, unique = false)
    public String likedBy;

    /** LIKED_DATETIME */
    @Column(nullable = false, unique = false)
    public Timestamp likedDatetime;

    /** room関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    public Room room;
}
