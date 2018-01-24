package jp.niconico.comment.model.entity.comment;

import jp.niconico.comment.model.entity.room.Room;
import jp.niconico.comment.model.entity.user.User;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 *
 */
@Generated("GSP")
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	/** COMMENT_ID */
	@Id
	@GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
	@Column(precision = 19, nullable = false, unique = true)
	public Long commentId;

	/** COMMENT */
	@Lob
	@Column(length = 2147483647, nullable = false, unique = false)
	public byte[] comment;

	/** COMMENT_DATETIME */
	@Column(nullable = true, unique = false)
	public Timestamp commentDatetime;

	/** USER_ID */
	@Column(precision = 19, nullable = false, unique = false)
	public Long userId;

	/** ROOM_ID */
	@Column(precision = 19, nullable = false, unique = false)
	public Long roomId;

	/** room関連プロパティ */
	@ManyToOne
	@JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
	public Room room;

	/** user関連プロパティ */
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	public User user;
}
