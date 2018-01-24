<%@page pageEncoding="UTF-8"%>

<div class="form-group">
	<label class="col-lg-2 control-label">ルーム名</label>
 	<div class="col-lg-10">
		<input type="text" name="roomName" class="form-control width-300" value="${f:h(roomName)}" />
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">詳細</label>
 	<div class="col-lg-10">
		<textarea name="detail" class="form-control" rows="4">${f:h(detail)}</textarea>
	</div>
</div>
<input type="hidden" name="roomId" value="${f:h(roomId)}" />