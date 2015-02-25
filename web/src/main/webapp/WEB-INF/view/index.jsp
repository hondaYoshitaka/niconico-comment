<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title"><bean:message key="titles.index.index" /></tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<div class="col-lg-12">
				<h2>Room</h2>
				<div class="list-group">
					<c:forEach var="room" items="${rooms}">
						<c:set var="roomShowUrl" value="Room#show?roomId=${f:h(room.roomId)}" />
						<a href="${ar:urlFor(roomShowUrl)}" class="list-group-item">
							<div class="title">
								${f:h(room.roomName)}
								<label class="label label-info pull-right label-like-room" data-room-id="${f:h(room.roomId)}">
									<i class="fa fa-thumbs-up"></i>
									${fn:length(room.likeRoomList)}
								</label>
							</div>
							<div class="detail" style="overflow: hidden; text-overflow: ellipsis; height: 1.2em; white-space: nowrap;">
								${f:h(room.detail)}
							</div>
						</a>
					</c:forEach>
				</div>

				<c:if test="${loginDto.isAdmin}">
					<h2>管理者用メニュー</h2>
					<a href="${ar:urlFor('Room#input')}">新規ルームを作る</a>
				</c:if>
			</div>
		</div>
	</tiles:put>
</tiles:insert>
