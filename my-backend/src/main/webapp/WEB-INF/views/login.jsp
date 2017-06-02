<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>Sign In</title>
	<link rel="stylesheet" href="/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/bootstrap.css">
	<link rel="stylesheet" href="/css/xenon-core.css">
	<link rel="stylesheet" href="/css/xenon-forms.css">
	<link rel="stylesheet" href="/css/xenon-components.css">
	<link rel="stylesheet" href="/css/xenon-skins.css">
	<link rel="stylesheet" href="/css/custom.css">
	<script src="/js/jquery-1.11.1.min.js"></script>
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	
</head>
<body class="page-body login-page">

	<div class="login-container">
		<div class="row">
		
			<div class="col-sm-6">
			
				<script type="text/javascript">
					jQuery(document).ready(function($) {
						// Reveal Login form
						setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);
						// Validation and Ajax action
						$("form#login").validate({
							rules: {
								username: {
									required: true
								},
								passwd: {
									required: true
								}
							},
							messages: {
								username: {
									required: 'Please enter your username.'
								},
								
								passwd: {
									required: 'Please enter your password.'
								}
							},
							// Form Processing via AJAX
							submitHandler: function(form)
							{
								show_loading_bar(70); // Fill progress bar to 70% (just a given value)
								var opts = {
									"closeButton": true,
									"debug": false,
									"positionClass": "toast-top-full-width",
									"onclick": null,
									"showDuration": "300",
									"hideDuration": "1000",
									"timeOut": "5000",
									"extendedTimeOut": "1000",
									"showEasing": "swing",
									"hideEasing": "linear",
									"showMethod": "fadeIn",
									"hideMethod": "fadeOut"
								};
								$.ajax({
									url: "/doLogin",
									method: 'GET',
									dataType: 'json',
									data: {
										username: $(form).find('#username').val(),
										password: $(form).find('#passwd').val()
									},
									success: function(resp) {
										show_loading_bar({
											delay: .5,
											pct: 100,
											finish: function(){
												// Redirect after successful login page (when progress bar reaches 100%)
												if(resp.errorNo == 200) {
													window.location.href = resp.url;
												} else {
													toastr.error("You have entered wrong password, please try again. User and password is <strong>demo/demo</strong> :)", "Invalid Login!", opts);
													//$passwd.select();
												}
											}
										});
									}
								});
							}
						});
						// Set Form focus
						$("form#login .form-group:has(.form-control):first .form-control").focus();
					});
				</script>
				
				<!-- Errors container -->
				<div class="errors-container">
				
				</div>
				
				<!-- Add class "fade-in-effect" for login form effect -->
				<form method="post" role="form" id="login" class="login-form fade-in-effect">
					<div class="login-header">
						<h1><a href="javascript:;" class="" style="color:#fff;">sign in</a></h1>
						<p>Dear user, log in to access the admin area!</p>
					</div>
					<div class="form-group">
						<label class="control-label" for="username">Username</label>
						<input type="text" class="form-control input-dark" name="username" id="username" autocomplete="off" />
					</div>
					<div class="form-group">
						<label class="control-label" for="passwd">Password</label>
						<input type="password" class="form-control input-dark" name="passwd" id="passwd" autocomplete="off" />
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-gray  btn-block text-left">
							<i class="fa-lock"></i>Log In
						</button>
					</div>
					<div class="login-footer">
						<a href="#">Forgot your password?</a>
					</div>
				</form>
				
			</div>
			
		</div>
	</div>


	<!-- Bottom Scripts -->
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/TweenMax.min.js"></script>
	<script src="/js/resizeable.js"></script>
	<script src="/js/joinable.js"></script>
	<script src="/js/xenon-api.js"></script>
	<script src="/js/xenon-toggles.js"></script>
	<script src="/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="/js/toastr/toastr.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="/js/xenon-custom.js"></script>

</body>
</html>