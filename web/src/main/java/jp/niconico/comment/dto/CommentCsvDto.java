package jp.niconico.comment.dto;

import com.orangesignal.csv.annotation.CsvColumn;
import com.orangesignal.csv.annotation.CsvEntity;

@CsvEntity(header = true)
public class CommentCsvDto {

	@CsvColumn(name = "ID", position = 0)
	public String commentId;

	@CsvColumn(name = "内容", position = 1)
	public String comment;

	@CsvColumn(name = "日時", position = 2)
	public String commentDatetime;
}
