<%@page pageEncoding="UTF-8"%>

<div class="form-group">
	<label class="col-lg-2 control-label">スケジュール名</label>
 	<div class="col-lg-10">
		<input type="text" name="scheduleName" class="form-control width-300" value="${f:h(scheduleName)}" />
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">開始時間</label>
 	<div class="col-lg-10">
	 	<div class="input-group">
			<input type="text" name="startDate" class="datepicker form-control width-150" value="${f:h(startDate)}" placeholder="日付" />
			<input type="text" name="startTime" class="timepicker form-control width-150" value="${f:h(startTime)}" placeholder="時間" />
	 	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">終了時間</label>
 	<div class="col-lg-10">
	 	<div class="input-group">
			<input type="text" name="endDate" class="datepicker form-control width-150" value="${f:h(endDate)}" placeholder="日付" />
			<input type="text" name="endTime" class="timepicker form-control width-150" value="${f:h(endTime)}" placeholder="時間" />
	 	</div>
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">詳細</label>
 	<div class="col-lg-10">
		<textarea name="detail" class="form-control" rows="4">${f:h(detail)}</textarea>
	</div>
	<input type="hidden" name="scheduleId" value="${f:h(scheduleId)}" />
</div>