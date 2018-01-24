<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:putList name="javascripts">
	</tiles:putList>
	<tiles:put name="title">
		結婚おめでとう！
	</tiles:put>

	<tiles:put name="content" type="string">
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
