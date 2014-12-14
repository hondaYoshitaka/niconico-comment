<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:putList name="javascripts">
		<tiles:add value="lib/jquery-ui.js" />
		<tiles:add value="lib/jquery.timepicker.js" />
		<tiles:add value="schedule.input.js" />
	</tiles:putList>
	<tiles:putList name="stylesheets">
		<tiles:add value="ui/jquery-ui.min.css" />
		<tiles:add value="jquery.timepicker.css" />
	</tiles:putList>
	<tiles:put name="title">
		スケジュール更新
	</tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<div class="col-lg-12">
				<section>
					<c:set var="updateUrl" value="Schedule#update?scheduleId=${f:h(scheduleId)}&roomId=${f:h(roomId)}" />
					<form action="${ar:urlFor(updateUrl)}" method="post" class="form-horizontal" role="form">
						<jsp:include page="/WEB-INF/view/schedule/_form.jsp"></jsp:include>
						<div class="btn-area">
							<button type="submit" class="btn btn-primary">
								スケジュールを更新する
							</button>
						</div>
					</form>
				</section>
				<ul class="pager">
				  <li class="previous">
				  	<c:set var="scheduleListUrl" value="Schedule#list?roomId=${f:h(roomId)}" />
				  	<a href="${ar:urlFor(scheduleListUrl)}">&larr; 戻る</a>
				  </li>
				</ul>
			</div>
		</div>
	</tiles:put>
</tiles:insert>
