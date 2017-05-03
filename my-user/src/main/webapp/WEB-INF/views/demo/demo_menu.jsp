<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>技术博客菜单管理</title>
</head>
<body class="page-body">
	<jsp:include page="../include/hidden_header.jsp"></jsp:include>
	
	<div class="page-container">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">菜单列表</h3>
							<div class="panel-options">
								<a href="#" id="" name="" onclick="edit(this)"><i class="fa-plus"></i></a>
								<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
							</div>
						</div>
						<table class="table table-striped" id="datatable"> 
							<thead>
								<tr>
									<th field="id">编号</th>
									<th field="name">菜单名称</th>
									<th field="op" field-role="2">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
		</div>
		<jsp:include page="../include/chat.jsp"></jsp:include>
	</div>
	
<jsp:include page="../dialog/dialog_demo_blog.jsp"></jsp:include>
<script>
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(5).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(5).find('ul li').eq(1).addClass('active');	
	
	$('#datatable').datatable({
		url_load : '/auth/demomenu/getList',
		backFn : function(p) {
			// console.log(p);
		}
	});
});

function edit(obj){
	var index = $(obj).attr('index');
	var id = $('#datatable tbody tr').eq(index).find('td').eq(0).text();
	var name = $('#datatable tbody tr').eq(index).find('td').eq(1).text();
	if($.commonUtil.isNotBlank(id)){
		$('input[name="id"]').val(id);
		$('input[name="name"]').val(name);	
	} else {
		$('input[name="id"]').val('');
		$('input[name="name"]').val('');
	}
	jQuery('#modal-edit').modal('show', {backdrop: 'static'});
}
function save() {
	var id = $('input[name="id"]').val();
	var name = $('input[name="name"]').val();
	var parm = {id:id,name:name};
	var url = '/auth/demomenu/save';
	$.fn.doSave(parm, url);
}
</script>
</body>
</html>