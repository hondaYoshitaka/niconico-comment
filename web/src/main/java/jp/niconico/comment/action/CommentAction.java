package jp.niconico.comment.action;

import static org.seasar.framework.util.IntegerConversionUtil.*;
import static org.seasar.framework.util.LongConversionUtil.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jp.niconico.comment.annotation.ana.Allow;
import jp.niconico.comment.dto.CommentCsvDto;
import jp.niconico.comment.dto.LoginDto;
import jp.niconico.comment.entity.Comment;
import jp.niconico.comment.entity.Room;
import jp.niconico.comment.entity.Schedule;
import jp.niconico.comment.entity.User;
import jp.niconico.comment.enums.Permissions;
import jp.niconico.comment.form.CommentForm;
import jp.niconico.comment.logic.CommentLogic;
import jp.niconico.comment.service.CommentService;
import jp.niconico.comment.service.RoomService;
import jp.niconico.comment.service.ScheduleService;
import jp.niconico.comment.service.UserService;
import jp.niconico.comment.util.DateUtil;
import net.arnx.jsonic.JSON;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.seasar.extension.jdbc.IterationCallback;
import org.seasar.extension.jdbc.IterationContext;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.StringConversionUtil;
import org.seasar.framework.util.tiger.CollectionsUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.CsvWriter;
import com.orangesignal.csv.io.CsvBeanWriter;

public class CommentAction {

	@ActionForm
	@Resource(name = "commentForm")
	public CommentForm form;

	@Resource
	public LoginDto loginDto;

	@Resource
	protected CommentService commentService;

	@Resource
	protected UserService userService;

	@Resource
	protected ScheduleService scheduleService;

	@Resource
	protected RoomService roomService;

	@Resource
	protected CommentLogic commentLogic;

	@Resource
	protected CsvConfig csvConfig;

	public List<Comment> comments;
	public List<Schedule> schedules;

	public Room room;

	/** chart.js用の配列文字列 */
	public String countArrayString = "[]";
	public String labelArrayString = "[]";

	/** 最新のコメントID */
	public String latestCommentId;

	@Allow(permission = Permissions.LOGIN)
	@Execute(validator = false)
	public String index() {
		return "list";
	}

	@Allow(permission = Permissions.LOGIN)
	@Execute(validator = true, input = "/")
	public String list() {
		room = roomService.findById(toLong(form.roomId));
		comments = commentService.findAllInRoom(toLong(form.roomId));
		for (Comment comment : comments) {
			comment.comment = commentLogic.maskBannedWord(comment.comment);
		}
		latestCommentId = commentLogic.findLatestCommentId(toLong(form.roomId));

		return "list.jsp";
	}

	@Allow(permission = Permissions.LOGIN)
	@Execute(validate = "validateBeforeSearch", input = "/")
	public String search() {
		List<BeanMap> results = CollectionsUtil.newArrayList();

		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("JST"));

		List<Comment> comments = commentService.findGreaterThanIdInRoom(toLong(form.roomId), toLong(form.commentId));
		for (Comment com : comments) {
			BeanMap map = new BeanMap();

			map.put("id", StringConversionUtil.toString(com.commentId));
			map.put("comment", commentLogic.maskBannedWord(com.comment));
			map.put("date", sdf.format(com.commentDatetime));
			results.add(map);
		}
		ResponseUtil.write(JSON.encode(results), "application/json", "UTF-8");
		return null;
	}

	@Allow(permission = Permissions.LOGIN)
	@Execute(validate = "validateBeforeCreate", input = "/")
	public String input() {
		room = roomService.findById(toLong(form.roomId));

		return "input.jsp";
	}

	@Allow(permission = Permissions.LOGIN)
	@Execute(validate = "validateBeforeCreate", input = "input")
	public String create() {
		Comment comment = Beans.createAndCopy(Comment.class, form).execute();
		comment.roomId = toLong(form.roomId);
		comment.userId = loginDto.userId;
		comment.commentDatetime = DateUtil.getCurrentTimestamp();

		commentService.insert(comment);

		return null;
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeDownload", input = "/")
	public String download() {
		HttpServletResponse response = ResponseUtil.getResponse();
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-disposition", "attachment; filename=\"room-comments.csv\"");

		final DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:MM:ss");
		format.setTimeZone(TimeZone.getTimeZone("JST"));

		try (final CsvBeanWriter<CommentCsvDto> writer = CsvBeanWriter.newInstance(new CsvWriter(response.getWriter(),
				csvConfig), CommentCsvDto.class, false);) {

			// メモリ消費を抑えるためにコメント１件ずつDBから読み出して、responseのprintWriterに順次出力する
			commentService.findAllInRoom(toLong(form.roomId), new IterationCallback<Comment, Integer>() {

				@Override
				public Integer iterate(Comment comment, IterationContext context) {
					CommentCsvDto csvDto = Beans.createAndCopy(CommentCsvDto.class, comment)
							.excludes("commentDatetime").execute();
					csvDto.commentDatetime = format.format(comment.commentDatetime);
					try {
						writer.write(csvDto);
						writer.flush();
					} catch (IOException e) {
						// オーバーライドしているのでRuntimeException以外の例外をthrowできない。
						throw new RuntimeException(e);
					}
					return 1;
				}
			});
		} catch (Exception e) {
			throw new RuntimeException("csvのダウンロードに失敗しました。", e);
		}
		return null;
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validator = true, input = "/")
	public String statistical() {
		schedules = scheduleService.findAllOrderByStartDate(toLong(form.roomId));

		return "statistical.jsp";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeDrawStatistical", input = "/")
	public String countChart() {
		Schedule schedule = scheduleService.findById(toLong(form.scheduleId));

		List<Long> countList = CollectionsUtil.newArrayList();
		List<String> labelList = CollectionsUtil.newArrayList();
		int eachMilliSec = toPrimitiveInt(form.eachSecond) * 1000;
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("JST"));

		for (Timestamp from = schedule.startDatetime;;) {
			if (from.after(schedule.endDatetime)) {
				break;
			}
			Timestamp to = new Timestamp(from.getTime() + eachMilliSec);
			long count = commentService.getCount(schedule.roomId, from, to);
			countList.add(count);
			labelList.add("'" + format.format(from) + "'");
			// 次のレンジ
			from.setTime(from.getTime() + eachMilliSec);
		}
		countArrayString = "[" + StringUtils.join(countList, ",") + "]";
		labelArrayString = "[" + StringUtils.join(labelList, ",") + "]";

		return "chart.jsp";
	}

	@Allow(permission = Permissions.ADMIN)
	@Execute(validate = "validateBeforeDrawStatistical", input = "/")
	public String findComment() {
		Schedule schedule = scheduleService.findById(toLong(form.scheduleId));
		comments = commentService.findByDate(schedule.roomId, schedule.startDatetime, schedule.endDatetime);

		return "schedule-comments.jsp";
	}

	/**
	 * createする前の検証を行います
	 * 
	 * @return
	 */
	public ActionMessages validateBeforeCreate() {
		ActionMessages errors = new ActionMessages();

		if (loginDto.userId == null) {
			return errors;
		}
		User user = userService.findById(loginDto.userId);
		if (user == null) {
			throw new RuntimeException("ログイン中のユーザが削除された、または、存在しません。");
		}
		Room room = roomService.findById(toLong(form.roomId));
		if (room == null) {
			throw new RuntimeException("ルームが存在しません。");
		}
		return errors;
	}

	/**
	 * searchする前の検証を行います
	 * 
	 * @return
	 */
	public ActionMessages validateBeforeSearch() {
		ActionMessages errors = new ActionMessages();

		Room room = roomService.findById(toLong(form.roomId));
		if (room == null) {
			throw new RuntimeException("ルームが存在しません。");
		}
		return errors;
	}

	/**
	 * downloadする前の検証を行います
	 * 
	 * @return
	 */
	public ActionMessages validateBeforeDownload() {
		ActionMessages errors = new ActionMessages();

		Room room = roomService.findById(toLong(form.roomId));
		if (room == null) {
			throw new RuntimeException("ルームが存在しません。");
		}
		return errors;
	}

	/**
	 * 統計グラフ/コメントを出すajax用の検証を行います。
	 * 
	 * @return
	 */
	public ActionMessages validateBeforeDrawStatistical() {
		ActionMessages errors = new ActionMessages();

		Schedule schedule = scheduleService.findById(toLong(form.scheduleId));
		if (schedule == null) {
			throw new RuntimeException("スケジュールが存在しません。");
		}
		return errors;
	}
}
