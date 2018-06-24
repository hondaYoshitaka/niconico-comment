<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:putList name="javascripts">
		<tiles:add value="lib/nicoscreen.js" />
		<tiles:add value="comment.list.js" />
	</tiles:putList>
	<tiles:put name="head" type="string"></tiles:put>
	<tiles:put name="title">
		${f:h(room.roomName)}: コメントライン
	</tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<div class="col-lg-12">
				<div class="tabbable tabs-below">
				  	<div class="tab-content">
				  		<%-- コメントライン表示タブ --%>
						<div id="tab-comment-line" class="tab-pane fade in active">
							 <div id="nicoscreen" data-latest-comment-id="${latestCommentId}" style="height: 600px; background:black;"></div>
						</div>
						<%-- コメントリスト表示タブ --%>
						<div id="tab-comment-list" class="tab-pane fade">
							<div style="overflow-x: scroll; height: 600px;">
								<table class="table table-striped table-bordered" id="comment-list-table">
									<thead>
										<tr>
											<th style="width: 40px;">#</th>
											<th>コメント</th>
											<th style="width: 200px;">日時</th>
											<c:if test="${loginDto.isAdmin}">
												<td style="width: 40px;">
												</td>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="com" items="${comments}">
											<c:set var="destroyUrl" value="Comment#destroy?commentId=${f:h(com.commentId)}" />
											<tr>
												<td>${f:h(com.commentId)}</td>
												<td>${f:h(com.comment)}</td>
												<td>
													<fmt:formatDate value="${com.commentDatetime}" pattern="yyyy/MM/dd HH:mm:ss" />
												</td>
												<c:if test="${loginDto.isAdmin}">
													<td>
														<a id="delete-comment" class="close" href="${ar:urlFor(destroyUrl)}">x</a>
													</td>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<label class="checkbox">
									<input type="checkbox" id="auto-scroll" checked="checked" /> 自動スクロールする
								</label>
							</div>
						</div>  
				  	</div>
				  	<ul class="nav nav-tabs">
					    <li class="active">
					    	<a href="#tab-comment-line" data-toggle="tab">コメントライン</a>
					    </li>
					    <li>
					    	<a href="#tab-comment-list" data-toggle="tab">コメントリスト</a>
					    </li>
					</ul>
				</div>
			</div>
			<c:set var="commentSearchUrl" value="Comment#search?roomId=${f:h(roomId)}" />
			<form action="${ar:urlFor(commentSearchUrl)}" class="ajax-form" method="GET">
			</form>
		</div>
	</tiles:put>
</tiles:insert>
