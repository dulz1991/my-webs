<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/meta.jsp"></jsp:include>
<title>个人中心</title>
</head>

<body>
	
    <div id="main-wrapper">
        <!-- Site Navigation -->
        <div id="menu" style="overflow:auto;overflow-y:auto;overflow-x:hidden;">
            <jsp:include page="include/left.jsp"></jsp:include>
        </div>
        
        <div id="container" style="overflow:auto;overflow-y:auto;overflow-x:hidden;">
        	<div class="col-xs-10 mg-top-20">
        		<div class="col-xs-4 bg-white" style="height:120px;">
        			<span style="position:relative;bottom:50px;">我的博客数量</span>
	        		<span id="indicatorContainer1"></span>
				</div>
				<div class="col-xs-4 bg-white float-right" style="height:120px;">
	        		<span style="position:relative;bottom:50px;">我关注的博客数量</span>
	        		<span id="indicatorContainer2"></span>
				</div>
			</div>
			<div class="col-xs-10 bg-white mg-top-20">
				<div class="col-sm-12">
					<h2><i class="fa fa-info"></i> 基本信息</h2>
					<div class="panel panel-default basic-panel">
						<div class="panel-body">
							<form role="form" id="basic-form" class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-username">昵称：</label>
									<div class="col-sm-10">
										<label class="col-sm-1 field-username control-label" for="field-username">${username}</label>
										<input type="text" class="form-control field-username display-none" name="username" value="${username}" id="field-1">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-mobile">手机：</label>
									<div class="col-sm-10">
										<label class="col-sm-1 control-label field-mobile" for="field-mobile">${phone}</label>
										<input type="text" class="form-control field-mobile display-none" name="phone" value="${phone}" id="field-2">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-email">邮箱：</label>
									<div class="col-sm-10">
										<label class="col-sm-1 control-label field-email" for="field-email">${email}</label>
										<input type="text" class="form-control field-email display-none" name="email" value="${email}" id="field-3">
									</div>
								</div>
								<div class="form-group-separator"></div>
								<span class="btn btn-info btn-small pull-right btn-edit display-none">编辑</span>
								<span class="btn btn-success btn-small pull-right btn-submit display-none">提交</span>
								<span class="btn btn-small pull-right btn-cancel  btn-default display-none">取消</span>
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
	$('ul.left-menu li a').eq(0).addClass('hover');
	var menuPosition = 0;
	$('ul.left-menu li').hover(function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$(this).offset().top-menuPosition);
	},function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$('.hover').offset().top-menuPosition);
	});
	
	$('#indicatorContainer1').radialIndicator({
        barColor: '#87CEEB',
        barWidth: 10,
        initValue: ${myBlogCount},
        roundCorner : true,
        percentage: false
    });
	$('#indicatorContainer2').radialIndicator({
        barColor: '#8744EB',
        barWidth: 10,
        initValue: ${followBlogCount},
        roundCorner : true,
        percentage: false
    });
	
	$('.btn-edit').click(function(){
		$(this).addClass('display-none');
		$('.btn-cancel').removeClass('display-none');
		$('.btn-submit').removeClass('display-none');
		$('input.field-email').removeClass('display-none');
		$('label.field-email').addClass('display-none');
	});
	$('.btn-cancel').click(function(){
		$(this).addClass('display-none');
		$('.btn-edit').removeClass('display-none');
		$('.btn-submit').addClass('display-none');
		$('input.field-email').addClass('display-none');
		$('label.field-email').removeClass('display-none');
	});
	$('.btn-submit').click(function(){
		
	});
});
</script>

</body>
</html>
