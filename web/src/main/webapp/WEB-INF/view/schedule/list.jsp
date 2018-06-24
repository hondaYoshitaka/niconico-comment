<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:putList name="javascripts">
	</tiles:putList>
	<tiles:put name="title">
		<bean:message key="titles.schedule.list" />
	</tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<div class="col-lg-12">
				<c:set var="scheduleInputUrl" value="Schedule#input?roomId=${f:h(roomId)}" />
				<c:if test="${empty(scheduleList)}">
					まだスケジュールが登録されていません。<br />
					スケジュールを<a href="${ar:urlFor(scheduleInputUrl)}">登録して</a>、コメントラインに表示させましょう！
				</c:if>
				<c:if test="${not empty(scheduleList)}">
					<a href="${ar:urlFor(scheduleInputUrl)}">
						<i class="fa fa-plus-square-o"></i>  スケジュールを追加する
					</a>
					<ul class="row schedule-list">
						<c:forEach var="schedule" items="${scheduleList}">
							<c:set var="editUrl" value="Schedule#edit?scheduleId=${f:h(schedule.scheduleId)}" />
							<c:set var="destroyUrl" value="Schedule#destroy?scheduleId=${f:h(schedule.scheduleId)}" />
							<li class="col-lg-6">
								<div class="schedule-content">
									<h2 class="schedule-title">
										${f:h(schedule.scheduleName)}
									</h2>
									<div class="schedule-time">
										<fmt:formatDate value="${schedule.startDatetime}" pattern="MM/dd HH:mm"/>
										〜
										<fmt:formatDate value="${schedule.endDatetime}" pattern="MM/dd HH:mm"/>
									</div>
									<div class="schedule-detail">
										${f:h(schedule.detail)}
									</div>
									<div class="operation-link-area">
										<a href="${ar:urlFor(editUrl)}">
											編集する
										</a>
										<a href="${ar:urlFor(destroyUrl)}">
											削除する
										</a>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				<ul class="pager">
				  <li class="previous">
				  	<c:set var="roomShowUrl" value="Room#show?roomId=${f:h(roomId)}" />
				  	<a href="${ar:urlFor(roomShowUrl)}">&larr; 戻る</a>
				  </li>
				</ul>
			</div>
		</div>
	</tiles:put>
</tiles:insert>
