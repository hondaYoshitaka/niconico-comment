package jp.niconico.comment.model.entity.comment;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 *
 */
@Generated("GSP")
@Entity
@Table(name = "banned_word")
public class BannedWord implements Serializable {

    private static final long serialVersionUID = 1L;

    /** BANNED_WORD_ID */
    @Id
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    @Column(precision = 19, nullable = false, unique = true)
    public Long bannedWordId;

    /** WORD */
    @Column(length = 255, nullable = true, unique = false)
    public String word;
}
