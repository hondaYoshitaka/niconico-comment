package jp.niconico.comment.task;

import java.sql.Timestamp;

import javax.annotation.Resource;

import jp.niconico.comment.service.S2sessionService;
import jp.niconico.comment.util.DateUtil;

import org.apache.commons.lang.StringUtils;
import org.seasar.chronos.core.TaskTrigger;
import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.trigger.CCronTrigger;
import org.seasar.chronos.core.trigger.CNonDelayTrigger;
import org.seasar.framework.env.Env;
import org.seasar.framework.log.Logger;

@Task
public class DbSessionCleanTask {

	@Resource
	protected S2sessionService s2sessionService;

	private final CCronTrigger trigger = new CCronTrigger("0 */10 * * * ?");

	private static final Logger logger = Logger.getLogger(DbSessionCleanTask.class);
	/** 有効期限 (ms) */
	private static final Long SESSION_MAX_AGE = 3L * 60L * 60L * 1000L;

	public void doExecute() {
		logger.info("dbsessionのクリーンを開始します。");
		try {
			int deleteCnt = deleteExpiredDbSession();
			logger.info("dbsessionの削除を実行しました。件数: " + deleteCnt);
		} catch (Exception e) {
			logger.fatal("想定外のエラーが発生しました。", e);
		}
		logger.info("dbsessionのクリーンを終了します。");
	}

	/**
	 * 有効期限切れになったdbsessionを削除します。
	 */
	protected int deleteExpiredDbSession() {
		Timestamp current = DateUtil.getCurrentTimestamp();
		return s2sessionService.deleteAllExpired(current, SESSION_MAX_AGE);
	}

	/**
	 * Taskのトリガーを取得します。<br>
	 * ct環境の場合、サーバ起動時に１度のみTaskが実行されます。
	 * 
	 * @return
	 */
	public TaskTrigger getTrigger() {
		if (StringUtils.equals(Env.getValue(), "ct")) {
			return new CNonDelayTrigger();
		}
		return trigger;
	}
}
