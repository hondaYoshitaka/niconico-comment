<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:putList name="javascripts">
		<tiles:add value="comment.input.js" />
	</tiles:putList>
	<tiles:put name="title">
		${f:h(room.roomName)}: 発言する
	</tiles:put>

	<tiles:put name="content" type="string">
		<%-- 常時、下に張り付くコンテナ --%>
		<div class="container position-bottom" style="padding-left: 0">
			<div class="row">
				<%-- スタンプリスト --%>
				<div id="comment-stamp-area" class="col-lg-12" style="display: none;">
					<ul id="comment-stamp-list">
						<li class="col-lg-2 col-xs-4">
							<a href="#" class="btn-auto-comment" data-comment="いいね！">
								<i class="fa fa-thumbs-o-up fa-2x"></i>
								<span class="fa-description">いいね</span>
							</a>
						</li>
						<li class="col-lg-2 col-xs-4">
							<a href="#" class="btn-auto-comment" data-comment="とても興味があります！">
								<i class="fa fa-heart-o fa-2x"></i>
								<span class="fa-description">興味あり</span>
							</a>
						</li>
						<li class="col-lg-2 col-xs-4">
							<a href="#" class="btn-auto-comment" data-comment="もっと詳しく教えて下さい！">
								<i class="fa fa-lightbulb-o fa-2x"></i>
								<span class="fa-description">もっと詳しく</span>
							</a>
						</li>
						<li class="col-lg-2 col-xs-4">
							<a href="#" class="btn-auto-comment" data-comment="ちょっと難しい…">
								<i class="fa fa-frown-o fa-2x"></i>
								<span class="fa-description">難しい</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<c:set var="commentCreateUrl" value="Comment#create?roomId=${f:h(roomId)}" />
				<form action="${ar:urlFor(commentCreateUrl)}" method="POST" class="ajax-form" role="form">
					<div class="col-lg-12">
					    <div class="input-group">
					      <span class="input-group-btn">
					        <a href="#" class="btn btn-default stamp-list-toggle">
					        	<i class="fa fa-smile-o fa-lg"></i>
							</a>
					      </span>
					      <input type="text" name="comment" class="form-control">
					      <span class="input-group-btn">
					        <a href="#" class="btn btn-primary" data-role="comment-trigger">送信</a>
					      </span>
					    </div>
					</div>
				</form>
			</div>
		</div>
	</tiles:put>
</tiles:insert>
