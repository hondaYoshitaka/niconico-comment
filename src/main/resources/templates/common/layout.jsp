<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
	<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1"/>
	
	<link href='http://fonts.googleapis.com/css?family=Audiowide' rel='stylesheet' type='text/css'>
	<link href="${f:url('/css/bootstrap.min.css')}" media="all" rel="stylesheet" type="text/css"/>
	<link href="${f:url('/css/font-awesome.css')}" media="all" rel="stylesheet" type="text/css"/>
	<link href="${f:url('/css/less-generated/cl.css')}" media="all" rel="stylesheet" type="text/css" />
	
	<tiles:importAttribute name="stylesheets" ignore="true" />
	<c:forEach var="stylesheet" items="${stylesheets}">
		<c:set var="cssUrl" value="/css/${stylesheet}" />
		<link href="${f:url(cssUrl)}" media="all" rel="stylesheet" type="text/css" />
	</c:forEach>

	<!--[if lte IE 8]>
		<script src="${f:url('/js/lib/html5shiv.js')}" type="text/javascript"></script>
		<script src="${f:url('/js/lib/respond.js')}" type="text/javascript"></script>
	<![endif]-->
	<script src="${f:url('/js/lib/jquery.js')}" type="text/javascript"></script>
	<script src="${f:url('/js/lib/bootstrap.js')}" type="text/javascript"></script>
	<script src="${f:url('/js/util.js')}" type="text/javascript"></script>
	<script type="text/javascript">
		var url = url ? url: {};
		url.loginUrl = "${ar:urlFor('Login#input')}";
	</script>

	<tiles:importAttribute name="javascripts" ignore="true" />
	<c:forEach var="javascript" items="${javascripts}">
		<c:set var="scriptUrl" value="/js/${javascript}" />
		<script type="text/javascript" src="${f:url(scriptUrl)}"></script>
	</c:forEach>

	<tiles:insert attribute="head" ignore="true"/>
	
	<title><tiles:getAsString name="title" /> | niconico comment</title>
	<link rel="shortcut icon" href="${f:url('/img/favicon.ico')}" />
</head>

<%-- fmt: jspに書き込まれるときにJSTに補正してもらう --%>
<fmt:setTimeZone value="JST" scope="request" />

<header>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<a href="${ar:urlFor('Login#logout')}" class="pull-right font-1x" title="ログアウト">
					<i class="fa fa-sign-out"></i>
				</a>
				<a href="${ar:urlFor('Index#index')}">
					<span class="logo-font font-1x">
						<span class="color-accent">nico</span>nico <span class="color-accent">comme</span>nt
					</span>
				</a>
			</div>
		</div>
	</div>
</header>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<html:errors />
		</div>
	</div>
</div>
<div id="main" class="container">
	<tiles:insert attribute="content" />
</div>