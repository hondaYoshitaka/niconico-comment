<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title"><bean:message key="titles.index.index" /></tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<h1 class="logo-font" style="text-align: center; margin-bottom: 50px;">
				<span class="color-accent">nico</span>nico <span class="color-accent">comme</span>nt
			</h1>
			<div class="col-lg-3">
				<a href="${ar:urlFor('Comment#input')}" class="thumbnail" style="text-align: center;">
					<i class="fa fa-comments-o fa-5x"></i>
					<br/><br/>発表者にコメントする
				</a>
			</div>
			<div class="col-lg-3">
				<a href="${ar:urlFor('Comment#list')}" class="thumbnail" style="text-align: center;">
					<i class="fa fa-play-circle fa-5x"></i>
					<br/><br/>コメントを眺める
				</a>
			</div>
			<c:if test="${isAdmin}">
				<div class="col-lg-3">
					<a href="${ar:urlFor('Schedule#index')}" class="thumbnail" style="text-align: center;">
						<i class="fa fa-calendar fa-5x"></i>
						<br/><br/>スケジュールを管理する
					</a>
				</div>
			</c:if>
			<c:if test="${isAdmin}">
				<div class="col-lg-3">
					<a href="${ar:urlFor('Comment#statistical')}" class="thumbnail" style="text-align: center;">
						<i class="fa fa-bar-chart-o fa-5x"></i>
						<br/><br/>コメントの統計を見る
					</a>
				</div>
			</c:if>
		</div>
	</tiles:put>
</tiles:insert>
