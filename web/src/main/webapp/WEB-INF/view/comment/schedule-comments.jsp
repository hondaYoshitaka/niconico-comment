<%@page pageEncoding="UTF-8"%>
<c:if test="${empty(comments)}">
		届いているコメントがありませんでした。
</c:if>
<c:if test="${not empty(comments)}">
	<table class="table table-striped table-bordered" id="comment-list-table">
		<thead>
			<tr>
				<th style="width: 40px;">#</th>
				<th>コメント</th>
				<th style="width: 200px;">日時</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="comment" items="${comments}">
				<tr>
					<td>${f:h(comment.commentId)}</td>
					<td>${f:h(comment.comment)}</td>
					<td>
						<fmt:formatDate value="${comment.commentDatetime}" pattern="MM/dd HH:ss:mm"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>