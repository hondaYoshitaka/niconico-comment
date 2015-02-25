<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title">
		${f:h(room.roomName)}: ルームメニュー
	</tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<div class="col-lg-12">
				<h2 class="room-show-title">${f:h(room.roomName)}</h2>
			    <div class="room-show-detail">
			    	${f:br(f:h(room.detail))}
			    </div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<c:set var="likeUrl" value="Room#like?roomId=${room.roomId}" />
				<a href="${ar:urlFor(likeUrl)}" class="thumbnail" style="text-align: center;">
					<i class="fa fa-thumbs-up fa-5x"></i>
					<br/><br/>この部屋にいいね！する
				</a>
			</div>
			<div class="col-md-3">
				<c:set var="commentInputUrl" value="Comment#input?roomId=${f:h(room.roomId)}" />
				<a href="${ar:urlFor(commentInputUrl)}" class="thumbnail" style="text-align: center;">
					<i class="fa fa-comments-o fa-5x"></i>
					<br/><br/>発表者にコメントする
				</a>
			</div>
			<div class="col-md-3 hidden-xs">
				<c:set var="commentListUrl" value="Comment#list?roomId=${f:h(room.roomId)}" />
				<a href="${ar:urlFor(commentListUrl)}" class="thumbnail" style="text-align: center;">
					<i class="fa fa-play-circle fa-5x"></i>
					<br/><br/>コメントを眺める
				</a>
			</div>
		</div>
		<c:if test="${loginDto.isAdmin}">
			<div class="row">
				<div class="col-md-12">
					<h3>管理者メニュー</h3>
				</div>
				<div class="col-md-3">
					<c:set var="scheduleIndexUrl" value="Schedule#index?roomId=${f:h(room.roomId)}" />
					<a href="${ar:urlFor(scheduleIndexUrl)}" class="thumbnail" style="text-align: center;">
						<i class="fa fa-calendar fa-5x"></i>
						<br/><br/>スケジュールを管理する
					</a>
				</div>
				<div class="col-md-3 hidden-xs">
					<c:set var="commentStatisticalUrl" value="Comment#statistical?roomId=${f:h(room.roomId)}" />
					<a href="${ar:urlFor(commentStatisticalUrl)}" class="thumbnail" style="text-align: center;">
						<i class="fa fa-bar-chart-o fa-5x"></i>
						<br/><br/>コメントの統計を見る
					</a>
				</div>
				<div class="col-md-3 hidden-xs">
					<c:set var="commentDownloadUrl" value="Comment#download?roomId=${f:h(room.roomId)}" />
					<a href="${ar:urlFor(commentDownloadUrl)}" class="thumbnail" style="text-align: center;">
						<i class="fa fa-cloud-download fa-5x"></i>
						<br/><br/>コメントをダウンロードする
					</a>
				</div>
			</div>
		</c:if>
	</tiles:put>
</tiles:insert>
