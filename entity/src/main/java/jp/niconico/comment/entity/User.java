package jp.niconico.comment.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** USER_ID */
    @Id
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    @Column(precision = 19, nullable = false, unique = true)
    public Long userId;

    /** USER_NAME */
    @Column(length = 255, nullable = true, unique = false)
    public String userName;

    /** PASSWORD */
    @Column(length = 255, nullable = true, unique = false)
    public String password;

    /** SALT */
    @Column(length = 255, nullable = true, unique = false)
    public String salt;

    /** IS_ADMIN */
    @Column(nullable = true, unique = false)
    public Boolean isAdmin;

    /** 有効期限 */
    @Column(nullable = false, unique = false)
    public Timestamp expiredAt;

    /** commentList関連プロパティ */
    @OneToMany(mappedBy = "user")
    public List<Comment> commentList;
}
