<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${title}</title>
	<script src="/ue/editor_config.js"></script>
	<script src="/ue/editor_all.js"></script>
	<link href="/ue/themes/default/ueditor.css" rel="stylesheet">
</head>
<body style="height:660px;">
	<div id="main-wrapper">
       
        <div id="menu">
            <jsp:include page="../include/left.jsp"></jsp:include>
        </div>
        
        <div id="container">
			<div class="col-xs-10 mg-top-20">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a><i class="fa fa-location-arrow"> ${title}</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<input type="hidden" value="${blog.id}" name="id" />
					            	 <input class="form-control" type="text" name="title" value="${blog.title}" placeholder="输入标题">
					          	</div>
					        	<div class="col-sm-4">
					        		<select class="form-control menuSelect" name="menuId">
					        			<option value="0">-- 选择类别 --</option>
					        			<c:forEach items="${menuList}" var="item">
					        				<option value="${item.id}">${item.name}</option>
					        			</c:forEach>
					        		</select>
					    		</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<b class="display-none">${blog.content}</b>
									<textarea id="myEditor">${blog.content}</textarea>
									<script type="text/javascript">
										var ue = new baidu.editor.ui.Editor();
										window.onload = render;
										function render() {
											ue.render('myEditor');
										}
									</script>
					        		<!-- <iframe name="myFrame" src="/iframe/code_edit.html"></iframe> -->
					        	</div> 	
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<a id="blogSubmit" class="btn btn-info" href="javascript:void(0);">提交</a>
					    		</div>
					        </div>
					     </form>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	
<script>
$(function(){
	//初始化左边栏菜单
	$('ul.left-menu li a').eq(3).addClass('hover');
	var menuPosition = $('ul.left-menu').offset().top;
	$('#lanPos').css('top',$('ul.left-menu li').eq(3).offset().top-menuPosition);
	$('ul.left-menu li').hover(function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$(this).offset().top-menuPosition);
	},function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$('.hover').offset().top-menuPosition);
	});
	
	$('.menuSelect').onSelect({
		compare : '${blog.menuId}',
		backFn : function(p) {
		}
	});
	
	$('#blogSubmit').click(function(){
		var elem = $('.form');
		$.ajax({ 
			url: "/auth/userCenter/saveBlog", 
			data:{
				id: elem.find('input[name="id"]').val(),
				menuId: elem.find('select[name="menuId"]').val(),
				title: elem.find('input[name="title"]').val(),
				content: ue.getContent()
			},  
			type: 'post',  
			cache: false,  
			dataType: 'json',
			success: function(data) {
				if(data.errorNo==200){
					window.location.href='http://my.blog/blog/detail?id='+data.id;
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