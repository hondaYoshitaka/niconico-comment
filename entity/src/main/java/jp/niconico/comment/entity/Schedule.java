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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 *
 */
@Generated("GSP")
@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /** SCHEDULE_ID */
    @Id
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    @Column(precision = 19, nullable = false, unique = true)
    public Long scheduleId;

    /** SCHEDULE_NAME */
    @Column(length = 255, nullable = false, unique = false)
    public String scheduleName;

    /** START_DATETIME */
    @Column(nullable = false, unique = false)
    public Timestamp startDatetime;

    /** END_DATETIME */
    @Column(nullable = false, unique = false)
    public Timestamp endDatetime;

    /** DETAIL */
    @Lob
    @Column(length = 65535, nullable = true, unique = false)
    public String detail;

    /** ROOM_ID */
    @Column(precision = 19, nullable = false, unique = false)
    public Long roomId;

    /** room関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    public Room room;
}
