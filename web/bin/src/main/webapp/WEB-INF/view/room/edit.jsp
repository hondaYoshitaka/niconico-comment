<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:putList name="javascripts">
	</tiles:putList>
	<tiles:put name="title">
		ルームを編集する
	</tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<div class="col-lg-12">
				<section>
					<c:set var="updateUrl" value="Room#update?roomId=${f:h(roomId)}" />
					<form action="${ar:urlFor(updateUrl)}" method="post" class="form-horizontal" role="form">
						<jsp:include page="/WEB-INF/view/room/_room_form.jsp"/>
						<div class="btn-area">
							<button type="submit" class="btn btn-primary">
								ルーム情報を更新する
							</button>
						</div>
					</form>
				</section>
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
