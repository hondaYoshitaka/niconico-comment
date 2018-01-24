<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:putList name="javascripts">
	</tiles:putList>
	<tiles:put name="title">
		ルームに入室する
	</tiles:put>

	<tiles:put name="content" type="string">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="logo-font" style="text-align: center; margin-bottom: 50px;">
					<span class="color-accent">nico</span>nico <span class="color-accent">comme</span>nt
				</h1>
				<form action="${ar:urlFor('Room#entry')}" method="POST" class="form-horizontal" role="form">
					<input type="hidden" name="roomId" value="${f:h(roomId)}" />
					<div class="form-group">
						<div class="col-lg-offset-3 col-lg-6">
							<input type="text" name="userName" class="form-control" placeholder="Enter name"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-offset-3 col-lg-6">
							<input type="password" name="password" class="form-control" placeholder="Password"/>
						</div>
					</div>
					<div class="btn-area">
						<button type="submit" class="btn btn-primary">
							<i class="fa fa-sign-in"></i> ログイン
						</button>
					</div>
				</form>
			</div>
		</div>
	</tiles:put>
</tiles:insert>
