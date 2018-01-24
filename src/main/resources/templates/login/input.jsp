<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
	<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1"/>
	
	<link href='http://fonts.googleapis.com/css?family=Audiowide' rel='stylesheet' type='text/css'>
	<link href="${f:url('/css/bootstrap.min.css')}" media="all" rel="stylesheet" type="text/css"/>
	<link href="${f:url('/css/font-awesome.css')}" media="all" rel="stylesheet" type="text/css"/>
	<link href="${f:url('/css/less-generated/cl.css')}" media="all" rel="stylesheet" type="text/css" />

	<!--[if lte IE 8]>
		<script src="${f:url('/js/lib/html5shiv.js')}" type="text/javascript"></script>
	<![endif]-->
	<script src="${f:url('/js/lib/jquery.js')}" type="text/javascript"></script>
	<script src="${f:url('/js/lib/bootstrap.js')}" type="text/javascript"></script>
	<script src="${f:url('/js/util.js')}" type="text/javascript"></script>
	
	<script src="${f:url('/js/comment.input.js')}" type="text/javascript"></script>

	<title>ログインする | niconico comment</title>
	<link rel="shortcut icon" href="${f:url('/img/favicon.ico')}" />
</head>
<body class="radial-gradient">
	<fmt:setTimeZone value="JST" scope="request" />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<html:errors />
			</div>
		</div>
	</div>
	<div id="main" class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="logo-font font-4x" style="text-align: center; margin-bottom: 50px;">
					<span class="color-accent">nico</span>nico <span class="color-accent">comme</span>nt
				</h1>
				<form action="${ar:urlFor('Login#login')}" method="POST" class="form-horizontal" role="form">
					<div class="form-group">
						<div class="col-lg-offset-3 col-lg-6">
							<input type="text" name="userName" value="${f:h(userName)}" class="form-control" placeholder="Enter name"/>
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
	</div>
</body>
</html>