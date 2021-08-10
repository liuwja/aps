<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
<title>质量信息管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Login and Registration Form with HTML5 and CSS3" />
<meta name="keywords"
	content="html5, css3, form, switch, animation, :target, pseudo-class" />
<meta name="author" content="Codrops" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/login/css/demo.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/login/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/login/css/animate-custom.css" />
<script src="<%=basePath%>resources/js/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	//<!--
	$(function() {
		$("#userName").focus();
	})
	function focusUname() {
		$(".keeplogin").html("&nbsp;");
		$("#userName").focus();
	}
//-->
</script>
</head>
<body>
	<div class = "content">
		<div class="container">
			<header>
				<h1>&nbsp;</h1>
			</header>
			<div class = "fotileLogo2017"></div>
			<div class = "QMSystem"><span>质量信息管理系统</span></div>
			<section>
				<div id="container_demo">
					<!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
					<a class="hiddenanchor" id="tologin"></a>
					<div id="wrapper">
						<div id="login" class="animate form">
							<form action="verifyLogin.do" autocomplete="off" method="post"
								onreset="focusUname()">
								<h1>系统登录</h1>
								<p>
									<!-- <label for="userName" class="uname" data-icon="u"> 用户名
									</label> --> <input id="userName" name="userName" required="required"
										type="text" placeholder="用户名" />
								</p>
								<p>
									<!-- <label for="password" class="youpasswd" data-icon="p">
										密码 </label> --> <input id="password" name="password" required="required"
										type="password" placeholder="密码" />
								</p>
								<p class="keeplogin">&nbsp;${msg }</p>
								<p class="login button">
									<input type="submit"
										value="" id = "loginButton"/><input type="reset" value="" id = "resetButton"/> 
								</p>
<!-- 								<p class="change_link">
									<img alt="" src="resources/login/images/fotile_logo.png"
										height="24px">
									                                    <span style="position: relative;top: -3px;font-weight: bolder;color: #CD950C;margin-left: 5px">因爱伟大</span>
								</p> -->
							</form>
						</div>



					</div>
				</div>
			</section>
		</div>
	</div>
</body>
</html>