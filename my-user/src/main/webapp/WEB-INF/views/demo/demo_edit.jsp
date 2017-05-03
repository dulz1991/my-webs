<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<script src="/mine/js/jquery.form.js"></script>
	<!-- <script src="/mine/js/jquery.ajaxfileupload.js"></script> -->
	<title>编辑demo</title>
<style>
</style>
</head>
<body class="page-body">
	<jsp:include page="../include/hidden_header.jsp"></jsp:include>
	
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a><i class="fa-location-arrow"> 编辑：${demo.title}</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" method="post" action="#" enctype="multipart/form-data">
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<input type="hidden" value="${demo.id}" name="id" />
					            	<input class="form-control" type="text" name="title" value="${demo.title}" placeholder="输入标题">
					          	</div>
					        	<div class="col-sm-3">
					        		<select class="form-control menuSelect" name="menuId">
										<option value="-1">-- 选择菜单 --</option>
										<c:forEach items="${menuList}" var="item">
										<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
					    		</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		选择demo包：
					        		<input type="file" name="attachFile" id="attachFile" accept=".zip" />
					        	</div> 	
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		demo描述：
									<textarea id="description" cols="120" rows="18">${demo.description}</textarea>
					        	</div> 	
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<a id="demoSubmit" class="btn btn-info" href="javascript:void(0);">提交</a>
					    		</div>
					        </div>
					     </form>
					</div>
				</div>
			</div>
		
		</div>
		<jsp:include page="../include/chat.jsp"></jsp:include>
	</div>
	
<script type="text/javascript">
$(function(){
	$('.menuSelect').onSelect({
		compare:'${demo.menuId}',
		backFn : function(p) {
		}
	});
	
	$('#demoSubmit').click(function(){
		$(".form").ajaxSubmit({  
            type:'post',  
            url:'/auth/demo/save',  
            dataType : 'json', //返回值类型 一般设置为json  
	        data:{
				description: $('#description').val()
			},
            success : function(data, status) {  
            	debugger;
	        	if(data.errorNo==200){
	        		self.location='http://my.demo/';
				} else {
					$.commonUtil.showTip(data.errorInfo);
				}
	        },  
	        error : function(data, status, e) {  
	        	$.commonUtil.showTip(data.errorInfo); 
	        }   
        }); 
		/* var elem = $('.form');
		$.ajaxFileUpload({
			url: "/auth/demo/save",
			secureuri: false, //一般设置为false  
			fileElementId: 'btnFile', //文件选择框的id属性
			type : 'post',  
	        dataType : 'json', //返回值类型 一般设置为json  
	        data:{
				id: elem.find('input[name="id"]').val(),
				menuId: elem.find('select[name="menuId"]').val(),
				title: elem.find('input[name="title"]').val(),
				description: $('#description').val()
			},
	        success : function(data, status) {  
	        	if(data.errorNo==200){
					window.location.href='127.0.0.1:8095';
				} else {
					$.commonUtil.showTip(data.errorInfo);
				}
	        },  
	        error : function(data, status, e) {  
	        	$.commonUtil.showTip(data.errorInfo); 
	        }  
		});  */
		/* $.ajax({ 
			url: "/auth/demo/save", 
			data:{
				id: elem.find('input[name="id"]').val(),
				menuId: elem.find('select[name="menuId"]').val(),
				title: elem.find('input[name="title"]').val(),
				description: $('#description').val(),
				demoPackage:$("#demo_package").val()
			},  
			type: 'post',  
			cache: false,  
			dataType: 'json',
			enctype: 'multipart/form-data',
			success: function(data) {
				debugger;
				if(data.errorNo==200){
					window.location.href='127.0.0.1:8095';
				} else {
					$.commonUtil.showTip(data.errorInfo);
				}
			}
		}); */
	});
	
	function progressHandlingFunction(e){
	    if(e.lengthComputable){
	        $('progress').attr({value:e.loaded,max:e.total});
	    }
	}
	
});
</script>
</body>
</html>