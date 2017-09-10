<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>编辑技术文档</title>
	<script src="/ue/editor_config.js"></script>
	<script src="/ue/editor_all.js"></script>
	<link href="/ue/themes/default/ueditor.css" rel="stylesheet">
	
	<link href="/css/lib/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<!-- head -->
	<jsp:include page="include/header.jsp"></jsp:include>
	
       <div id="container">
		<div class="col-xs-10 mg-top-20">
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal form" action="javascript:void(0);">
				        <div class="form-group">
				          	<div class="col-sm-12">
				          		<input type="hidden" value="${blog.id}" name="id" />
				          		<input type="hidden" value="${menuId}" name="menuId" />
				            	<input class="form-control " type="text" name="title" value="${blog.title}" placeholder="输入标题">
				          	</div>
				        </div>
				        
				        <div class="form-group">
				        	<div class="col-sm-12">
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
				        	<div class="col-sm-12" style="padding-bottom:30px;">
				        		<a id="blogSubmit" class="btn btn-info" href="javascript:void(0);">提交</a>
				    		</div>
				        </div>
				     </form>
				</div>
			</div>
		</div>
	</div>
	
<script>
$(function(){
	
	$('#blogSubmit').click(function(){
		var elem = $('.form');
		$.ajax({ 
			url: "/auth/blog/save", 
			data:{
				id: elem.find('input[name="id"]').val(),
				menuId: elem.find('input[name="menuId"]').val(),
				title: elem.find('input[name="title"]').val(),
				content: ue.getContent()
			},  
			type: 'post',  
			cache: false,  
			dataType: 'json',
			success: function(data) {
				if(data.errorNo==200){
					window.location.href='/blog/index?blogId='+data.id;
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