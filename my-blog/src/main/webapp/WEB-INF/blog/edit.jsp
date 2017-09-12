<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>编辑技术文档</title>
	<script src="/ue/ueditor.config.js"></script>
	<script src="/ue/ueditor.all.js"></script>
	<link href="/ue/themes/default/css/ueditor.css" rel="stylesheet">
	
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
					    		<i class="fa fa-location-arrow"></i> <c:if test="${not empty blogMenu.name }">${blogMenu.name }</c:if>  ><c:if test="${not empty blog.title }"><a href="/blog?blogId=${blog.id }" id="inputTitle" style="display:inline">${blog.title }</a></c:if><c:if test="${empty blog.title }"><a href="javascript:;" id="inputTitle" style="display:inline"></a></c:if>
					        </div>
					    </div>
					
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
									var ue = UE.getEditor('myEditor',{
								       initialFrameHeight:500,
								       initialFrameWidth:'100%',
								       serverUrl:'/ue/jsp/controller.jsp?imgType=blog'
								       /* autoHeightEnabled: true,
								       autoFloatEnabled: true */
									});
								</script>
								<!-- <script type="text/javascript">
									var ue = new baidu.editor.ui.Editor();
									window.onload = render;
									function render() {
										ue.render('myEditor');
									}
								</script> -->
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
	$('input[name="title"]').keyup(function(){
		$('#inputTitle').text($('input[name="title"]').val());
	});
	
	$('input[name="title"]').blur(function(){
		$('#inputTitle').text($('input[name="title"]').val());
	});
	
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