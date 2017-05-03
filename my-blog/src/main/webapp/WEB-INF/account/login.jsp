<!DOCTYPE html>
<html>	
<head>
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link href="/css/login.css" rel='stylesheet' type='text/css' />
<!--//webfonts-->
<script src="/js/lib/jquery-1.10.2.min.js"></script>
</head>
<body>
<script>
$(document).ready(function(c) {
	 $('#btn-submit').click(function(){
		 $.ajax({
				url: "/account/doLogin",
				method: 'GET',
				dataType: 'json',
				data: {
					username: $('.username').val(),
					password: $('.password').val()
				},
				success: function(data) {
					if (data.errorNo == 200) {
						window.location.href=data.url;
					} else {
						alert(data.errorInfo);
					}
				}
			}); 
	 });
});
</script>
 
 	<h1>Sign In Form</h1>
	<div class="login-form">
		<div class="head-info">
			<label class="lbl-1"> </label>
			<label class="lbl-2"> </label>
			<label class="lbl-3"> </label>
		</div>
		<div class="clear"></div>
		<div class="avtar">
			<img src="/img/login/avtar.png" />
		</div>
		<form>
			<input type="text" class="username"  value="" placeHolder="Input username here" >
			<div class="key">
				<input type="password" class="password" value="" placeHolder="Input password here">
			</div>
		</form>
		<div class="signin">
			<input type="submit" value="Login" id="btn-submit" >
		</div>
	</div>

 	<div class="copy-rights">
		<p>Copyright &copy; Copyright 2016 my.blog Designed</p>
	</div>

</body>
</html>