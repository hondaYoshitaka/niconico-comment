<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:putList name="javascripts">
		<tiles:add value="lib/Chart.js" />
		<tiles:add value="comment.statistical.js" />
	</tiles:putList>
	<tiles:put name="head" type="string">
	</tiles:put>
	<tiles:put name="title">
		コメント統計
	</tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<div class="col-lg-12">
				<form data-chart-action="${ar:urlFor('Comment#countChart')}" data-list-action="${ar:urlFor('Comment#findComment')}"
					method="post" class="ajax-form form-inline" role="form">
					<div class="form-group">
						<select name="scheduleId" id="select-schedule" class="form-control width-300">
							<option value=""> -- スケジュールを選択 -- </option>
							<c:forEach var ="sch" items="${schedules}">
								<option value="${sch.scheduleId}">
									${f:h(sch.scheduleName)}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<select name="eachSecond" id="select-each-second" class="form-control width-100">
							<option value="60">1分</option>
							<option value="120">2分</option>
							<option value="180">3分</option>
							<option value="240">4分</option>
							<option value="300" selected="selected">5分</option>
						</select>
						ごとのコメント
					</div>
				</form>
			</div>
		</div>
		<%-- 統計チャート --%>
		<div class="row">
			<div class="col-lg-12">
				<div id="chart-area"></div>
			</div>
		</div>
		<%-- コメントのテーブル --%>
		<div class="row">
			<div class="col-lg-12">
				<div id="comment-list-area" style="margin-top: 20px;"></div>
			</div>
		</div>
	</tiles:put>
</tiles:insert>
