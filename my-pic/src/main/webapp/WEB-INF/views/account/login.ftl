<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Journey 登陆</title>
<#include "../share/meta.ftl">
</head>
<body>

	<!-- header -->
    <#include "../share/nav.ftl">

	<br /><br />
	<div id="options" class="section scrollspy hoverable" style="width:60%;margin:auto auto;">
		<h2 class="header center">登陆</h2>
    	<div class="row">
    	<form class="col s12">
        	<div class="row">
            	<div class="input-field col s12">
                	<i class="fa fa-user prefix"></i>
                	<input id="icon_prefix" type="text" class="validate username">
                	<label for="icon_prefix" class="">用户名</label>
              	</div>
			</div>
			<div class="row">
              	<div class="input-field col s12">
                	<i class="fa fa-key fa-fw prefix"></i>
                	<input id="icon_telephone" type="tel" class="validate password">
                	<label for="icon_telephone" class="">密码</label>
              	</div>
            </div>
			<div class="row" style="margin-top:-40px;">
            	<div class="input-field col s12">
					<p>
      					<input type="checkbox" id="test5" />
      					<label for="test5">记住密码?</label>
    				</p>
				</div>
            </div>

			<div class="row">
              	<div class="input-field col s12" style="text-align:right;">
                	<a class="waves-effect waves-light btn-large pink" id="btn-submit" href="javascript:;"><i class="fa fa-sign-in">&nbsp;登陆</i></a>
					<a class="waves-effect waves-light btn-large" href="/"><i class="fa fa-home">&nbsp;首页</i></a>
              	</div>
            </div>
         </form>
      </div>	
    </div>

	<br /><br />
	<!-- footer -->
	<#include "../share/footer.ftl">
	
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
						window.location.href="/";
					} else {
						alert(data.errorInfo);
					}
				}
			}); 
	 });
});
</script>
</body>
</html>