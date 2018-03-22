<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>	
<head>
	<title>Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
	<link rel="icon" type="image/png" href="/img/favicon.png" />
	
	<style>
		*,:after,:before{box-sizing:border-box}
		.clearfix:after,.clearfix:before{content:'';display:table}
		.clearfix:after{clear:both;display:block}
		a{color:inherit;text-decoration:none;}
		input, button {
		  outline: none;
		  border: none;
		}
		.login-wrap{
			width:100%;
			margin:auto;
			max-width:525px;
			min-height:480px;
			position:relative;
			background:url(/img/login/bg2.jpg) no-repeat center;
			box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
		}
		.login-html{
			width:100%;
			height:100%;
			position:absolute;
			padding:30px 60px;
			background:rgba(40,57,101,.9);
		}
		.login-html .sign-in-htm,
		.login-html .sign-up-htm{
			top:0;
			left:0;
			right:0;
			bottom:0;
			position:absolute;
			-webkit-transform:rotateY(180deg);
			        transform:rotateY(180deg);
			-webkit-backface-visibility:hidden;
			        backface-visibility:hidden;
			-webkit-transition:all .4s linear;
			        transition:all .4s linear;
		}
		.login-html .sign-in,
		.login-html .sign-up,
		.login-form .group .check{
			display:none;
		}
		.login-html .tab,
		.login-form .group .label,
		.login-form .group .button{
			color:#aaa;
			text-transform:uppercase;
		}
		.login-html .tab{
			font-size:22px;
			margin-right:15px;
			padding-bottom:5px;
			margin:0 15px 10px 0;
			display:inline-block;
			border-bottom:2px solid transparent;
		}
		.login-html .sign-in:checked + .tab,
		.login-html .sign-up:checked + .tab{
			color:#fff;
			border-color:#1161ee;
		}
		.login-form{
			min-height:345px;
			position:relative;
			-webkit-perspective:1000px;
			        perspective:1000px;
			-webkit-transform-style:preserve-3d;
			        transform-style:preserve-3d;
		}
		.login-form .group{
			margin-bottom:15px;
		}
		.login-form .group .label,
		.login-form .group .input,
		.login-form .group .button{
			width:100%;
			color:#fff;
			display:block;
		}
		.login-form .group .input,
		.login-form .group .button{
			border:none;
			padding:15px 20px;
			border-radius:25px;
			background:rgba(255,255,255,.1);
		}
		.login-form .group input[data-type="password"]{
			text-security:circle;
			-webkit-text-security:circle;
		}
		.login-form .group .label{
			color:#aaa;
			font-size:12px;
		}
		.login-form .group .button{
			background:#1161ee;
		}
		.login-form .group label .icon{
			width:15px;
			height:15px;
			border-radius:2px;
			position:relative;
			display:inline-block;
			background:rgba(255,255,255,.1);
		}
		.login-form .group label .icon:before,
		.login-form .group label .icon:after{
			content:'';
			width:10px;
			height:2px;
			background:#fff;
			position:absolute;
			-webkit-transition:all .2s ease-in-out 0s;
			        transition:all .2s ease-in-out 0s;
		}
		.login-form .group label .icon:before{
			left:3px;
			width:5px;
			bottom:6px;
			-webkit-transform:scale(0) rotate(0);
			    -ms-transform:scale(0) rotate(0);
			        transform:scale(0) rotate(0);
		}
		.login-form .group label .icon:after{
			top:6px;
			right:0;
			-webkit-transform:scale(0) rotate(0);
			    -ms-transform:scale(0) rotate(0);
			        transform:scale(0) rotate(0);
		}
		.login-form .group .check:checked + label{
			color:#fff;
		}
		.login-form .group .check:checked + label .icon{
			background:#1161ee;
		}
		.login-form .group .check:checked + label .icon:before{
			-webkit-transform:scale(1) rotate(45deg);
			    -ms-transform:scale(1) rotate(45deg);
			        transform:scale(1) rotate(45deg);
		}
		.login-form .group .check:checked + label .icon:after{
			-webkit-transform:scale(1) rotate(-45deg);
			    -ms-transform:scale(1) rotate(-45deg);
			        transform:scale(1) rotate(-45deg);
		}
		.login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
			-webkit-transform:rotate(0);
			    -ms-transform:rotate(0);
			        transform:rotate(0);
		}
		.login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
			-webkit-transform:rotate(0);
			    -ms-transform:rotate(0);
			        transform:rotate(0);
		}
		
		.hr{
			height:2px;
			margin:20px 0 40px 0;
			background:rgba(255,255,255,.2);
		}
		.foot-lnk{
			color:#ccc;
			text-align:center;
		}
		.label-left{text-align:left;font-size:18px;padding-left:15px;}
	</style>
	
</head>
<body>
	<div class="jq22-container">
		<div class="login-wrap">
			<div class="login-html">
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
				<div class="login-form">
					<div class="sign-in-htm">
						<div class="group">
							<label for="user" class="label label-left">用户名</label>
							<input id="user" name="loginUsername" type="text" class="input" placeholder="请输入用户名">
						</div>
						<div class="group">
							<label for="pass" class="label label-left">密码</label>
							<input id="pass" name="loginPwd" type="password" class="input" data-type="password" placeholder="请输入密码">
						</div>
						<div class="group label-left">
							<input id="check" type="checkbox" class="check" checked>
							<label for="check"><span class="icon"></span> 保持登录</label>
						</div>
						<div class="group">
							<input type="button" class="button" onclick="$.common.doLogin()" value="登录">
						</div>
						<div class="group">
							<div class="swal2-validationerror" id="loginError" style="display: none;background:rgba(0,0,0,0)"></div>
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<a href="#forgot">忘记密码</a>
						</div>
					</div>
					<div class="sign-up-htm">
						<div class="group">
							<label for="user" class="label label-left">用户名</label>
							<input id="user" type="text" class="input" placeholder="请输入注册用户名">
						</div>
						<div class="group">
							<label for="pass" class="label label-left">密码</label>
							<input id="pass" type="password" class="input" data-type="password" placeholder="请输入密码">
						</div>
						<div class="group">
							<label for="pass" class="label label-left">确认密码</label>
							<input id="pass" type="password" class="input" data-type="password" placeholder="请输入确认密码">
						</div>
						<div class="group">
							<label for="pass" class="label label-left">邮箱</label>
							<input id="pass" type="text" class="input" placeholder="请输入邮箱">
						</div>
						<div class="group">
							<input type="submit" class="button" value="注册">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>