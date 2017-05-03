<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/meta.jsp"></jsp:include>
<title>修改密码</title>
</head>

<body  style="background-color:#f0f0f0;">
	
    <div id="main-wrapper">
        <!-- Site Navigation -->
        <div id="menu" style="overflow:auto;overflow-y:auto;overflow-x:hidden;">
            <jsp:include page="include/left.jsp"></jsp:include>
        </div>
        
        <div id="container" style="overflow:auto;overflow-y:auto;overflow-x:hidden;">
			<div class="col-xs-10 bg-white mg-top-20">
				<div class="col-sm-12">
					<h2><i class="fa fa-key"></i> 修改密码</h2>
					<div class="panel panel-default pwd-panel">
						<div class="panel-body">
							<form role="form" id="pwd-form" class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-4">旧密码</label>
									<div class="col-sm-10">
										<input type="password" class="form-control" name="oldpwd" id="field-4">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-5">新密码</label>
									<div class="col-sm-10">
										<input type="password" class="form-control" name="newpwd" id="field-5">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-6">确认密码</label>
									<div class="col-sm-10">
										<input type="password" class="form-control" name="cfmpwd" id="field-6">
									</div>
								</div>
								<div class="form-group-separator"></div>
								<span class="btn btn-info btn-small btn-icon pull-right btn-submit">提交</span>
							</form>
						</div>
					</div>
				</div>
			</div>
        </div>
    </div>

<script type="text/javascript">
$(function(){
	//初始化左边栏菜单
	$('ul.left-menu li a').eq(5).addClass('hover');
	var menuPosition = $('ul.left-menu').offset().top;
	$('#lanPos').css('top',$('ul.left-menu li').eq(5).offset().top-menuPosition);
	$('ul.left-menu li').hover(function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$(this).offset().top-menuPosition);
	},function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$('.hover').offset().top-menuPosition);
	});
	
	$('.btn-submit').click(function(){
		$.ajax({ 
			url: "/auth/userCenter/modifyPwd",
			data:{
				oldpwd: $('input[name="oldpwd"]').val(),
				newpwd: $('input[name="newpwd"]').val(),
				cfmpwd: $('input[name="cfmpwd"]').val()
			},  
			type: 'post',  
			cache: false,  
			dataType: 'json',
			success: function(data) {
				if(data.errorNo==200){
					$('input[name="oldpwd"]').val('');
					$('input[name="newpwd"]').val('');
					$('input[name="cfmpwd"]').val('');
				} else {
				}
				alert(data.errorInfo);
			}
		});
	});
	
});
</script>

</body>
</html>
