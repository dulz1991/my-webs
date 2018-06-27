<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>后台管理系统登录</title>
	<meta charset="utf-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="/assets/img/logo.png" title="" rel="shortcut icon" type="image/x-icon">
	
	<!--Basic Styles-->
	<link href="/assets/css/bootstrap.min.css"  title="" rel="stylesheet" />
	<link href="/assets/css/bootstrap-multiselect.css"  title="" rel="stylesheet" />
	<link href="/assets/css/font-awesome.min.css" title="" rel="stylesheet" />
	<!--Beyond styles-->
	<link href="/assets/css/beyond.min.css" title="" id="beyond-link"  rel="stylesheet" />
	<!-- <link href="/assets/css/typicons.min.css" title="" rel="stylesheet" /> -->
	<link href="/assets/css/animate.min.css" title="" rel="stylesheet" />
</head>

<body>
	<div class="login-container animated fadeInDown">
        <div class="loginbox bg-white">
        
            <div class="loginbox-title" >登录</div>
            
            <div class="loginbox-social">
                <div class="social-title ">欢迎登录后台管理系统</div>
                <div class="social-buttons" style="text-align:center">
                        <!-- <img alt="" src="/assets/img/logo.png" width="80px;"> -->
                </div>
            </div>
            
            <div class="loginbox-textbox">
                <input type="text" name="username" class="form-control" placeholder="登录名">
            </div>
            <div class="loginbox-textbox">
                <input type="password" name="password" class="form-control" placeholder="密码">
            </div>
            <!-- <div class="loginbox-forgot">
                <a href="">Forgot Password?</a>
            </div> -->
            <div class="loginbox-submit">
                <input type="button" class="btn btn-primary btn-block" onclick="toLogin()" value="登录">
            </div>
            <!-- <div class="loginbox-signup">
                <a href="register.html">Sign Up With Email</a>
            </div> -->
        </div>
        <!-- <div class="logobox">
        </div> -->
    </div>
	
	<script src="/assets/js/jquery-2.0.3.min.js"></script>
	<script src="/assets/js/toastr/toastr.js"></script>
	<script src="/assets/js/bootstrap.min.js"></script>
	<!-- layer -->
	<script type="text/javascript" src="/js/plugins/layer/layer.js"></script>
	<!-- 自定义 js -->
	<script type="text/javascript" src="/js/custom/my.common.js"></script>
	<script type="text/javascript" src="/js/custom/my.pop.js"></script>
	<!--Beyond Scripts-->
	<script src="/assets/js/beyond.min.js"></script>
	
	<script type="text/javascript">
		function toLogin(){
			$('.loginbox-submit input').val('登录中...');
			var username = $('input[name="username"]').val();
			var password = $('input[name="password"]').val();
			var parm = {username:username, password:password};
			$.common.post(parm, '/doLogin', function(data){
				if(data.errorNo!=0){
					$.pop.error(data.errorInfo);
					$('.loginbox-submit input').val('登录');
				} else {
					if($.common.isNotBlank(data.url)){
						location.href=data.url;
					} else {
						location.href='/index';
					}
				}
			});
		}
	</script>
					
</body>
</html>