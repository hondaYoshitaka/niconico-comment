package jp.niconico.comment.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 
 *
 */
@Generated("GSP")
@Entity
@Table(name = "s2session")
public class S2session implements Serializable {

    private static final long serialVersionUID = 1L;

    /** SESSION_ID */
    @Id
    @Column(length = 255, nullable = false, unique = false)
    public String sessionId;

    /** NAME */
    @Id
    @Column(length = 255, nullable = false, unique = false)
    public String name;

    /** VALUE */
    @Lob
    @Column(length = 2147483647, nullable = true, unique = false)
    public byte[] value;

    /** LAST_ACCESS */
    @Column(nullable = true, unique = false)
    public Timestamp lastAccess;
}
