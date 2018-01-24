<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:putList name="javascripts">
	</tiles:putList>
	<tiles:put name="title">
		ルームを作る
	</tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<div class="col-lg-12">
				<form action="${ar:urlFor('Room#create')}" method="post" class="form-horizontal" role="form">
					<jsp:include page="/WEB-INF/view/room/_room_form.jsp"/>
					<div class="btn-area">
						<button type="submit" class="btn btn-primary">
							新しいルームを作る
						</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<ul class="pager">
				  <li class="previous">
				  	<a href="${ar:urlFor('Index#index')}">&larr; 戻る</a>
				  </li>
				</ul>
			</div>
		</div>
	</tiles:put>
</tiles:insert>
