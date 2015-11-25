<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>乌拉特前旗干部考核系统</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script src="voteSystem/dist/js/vendor/jquery.min.js"></script>
    <style>
body {
	background-color: #FEFEFE;
}

.wrap {
	width: 960px;
	margin: 100px auto;
	font-size: 125%;
}

.row {
	margin: 30px 0;
}
</style>
    
    <link href="voteSystem/dist/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="voteSystem/dist/css/flat-ui.css" rel="stylesheet">
    <link href="voteSystem/docs/assets/css/demo.css" rel="stylesheet">

    <link rel="shortcut icon" href="voteSystem/img/favicon.ico">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="dist/js/vendor/html5shiv.js"></script>
    <script src="dist/js/vendor/respond.min.js"></script>
    <![endif]-->
</head>
<body style="height: 100%; background: #1ABC9C;">
<div style="width: 80%; margin: 0 auto; margin-top: 100px;">
	<form action="login.action" method="post">
    <div class="login-form">
        <div class="form-group">
            <a>乌拉特前旗干部考核系统</a>
        </div>
        <div class="form-group">
            <input type="text" name="username" class="form-control login-field" value="" placeholder="请输入用户名" id="login-name">
            <label class="login-field-icon fui-user" for="login-name"></label>
        </div>
		<s:token name="loginToken"></s:token>
        <div class="form-group">
            <input type="password" name="password" class="form-control login-field" value="" placeholder="请输入密码" id="login-pass">
            <label class="login-field-icon fui-lock" for="login-pass"></label>
        </div>
        
        <div class="row" style="">
				<div id="div_geetest_lib"></div>
				<div id="div_id_embed"></div>

				<%--End  Code--%>

				<script type="text/javascript">
				
				<%--
				function geetest_ajax_results() {
					//TODO, not necessory a geetest ajax demo,
					$.ajax({
						url : "/gt-java-sdk/VerifyLoginServlet",//todo:set the servelet of your own
						type : "post",
						data : gt_captcha_obj.getValidate(),
						success : function(sdk_result) {
							console.log(sdk_result)
						}
					});
				}
				--%>
				
					var gtFailbackFrontInitial = function(result) {
						var s = document.createElement('script');
						s.id = 'gt_lib';
						s.src = 'http://static.geetest.com/static/js/geetest.0.0.0.js';
						s.charset = 'UTF-8';
						s.type = 'text/javascript';
						document.getElementsByTagName('head')[0].appendChild(s);
						var 
					loaded = false;
						s.onload = s.onreadystatechange = function() {
							if (!loaded
									&& (!this.readyState
											|| this.readyState === 'loaded' || this.readyState === 'complete')) {
								loadGeetest(result);
								loaded = true;
							}
						};
					}
					//get  geetest server status, use the failback solution


					var loadGeetest = function(config) {

						//1. use geetest captcha
						window.gt_captcha_obj = new window.Geetest({
							gt : config.gt,
							challenge : config.challenge,
							product : 'embed',
							offline : !config.success
						});

						gt_captcha_obj.appendTo("#div_id_embed");

						//Ajax request demo,if you use submit form ,then ignore it 
						gt_captcha_obj.onSuccess(function() {
							geetest_ajax_results()
						});
					}

					s = document.createElement('script');
					s.src = 'http://api.geetest.com/get.php?callback=gtcallback';
					$("#div_geetest_lib").append(s);
					
					var gtcallback =( function() {
						var status = 0, result, apiFail;
						return function(r) {
							status += 1;
							if (r) {
								result = r;
								setTimeout(function() {
									if (!window.Geetest) {
										apiFail = true;
										gtFailbackFrontInitial(result)
									}
								}, 1000)
							}
							else if(apiFail) {
								return
							}
							if (status == 2) {
								loadGeetest(result);
							}
						}
					})()
					
					$.ajax({
								url : "StartCaptchaServlet",
								type : "get",
								dataType : 'JSON',
								success : function(result) {
									gtcallback(result)
								}
							})
				</script>
			</div>
        
        <s:fielderror name="errorInfo"></s:fielderror>
        <input class="btn btn-primary btn-lg btn-block" type="submit" value="登录"/>
        <a class="login-link" href="#">忘记了你的密码？</a>
    </div>
    </form>
</div>
<script src="voteSystem/dist/js/vendor/jquery.min.js"></script>
<script src="voteSystem/dist/js/vendor/video.js"></script>
<script src="voteSystem/dist/js/flat-ui.min.js"></script>
<script src="voteSystem/docs/assets/js/application.js"></script>
<script>
    function login(){
        document.location="html/user.html";
    }
</script>
</body>
</html>

