<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>demo管理</title>
</head>
<body class="page-body">
	<jsp:include page="../include/hidden_header.jsp"></jsp:include>
	
	<div class="page-container">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="col-sm-12 panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">搜索框</h3>
						<div class="panel-options">
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" id="form" action="javascript:void(0);">
							<div class="form-group">
								<div class="col-sm-3">
									标题:
									<input type="text" class="form-control input" name="title" value="" placeholder="输入标题">
								</div>
								<div class="col-sm-3">
									菜单：
									<select class="form-control select" name="menuId">
										<option value="-1">-- 选择菜单 --</option>
										<c:forEach items="${menuList}" var="item">
										<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-2">
									<br>
									<button class="btn btn-info btn-icon" onclick="search()">
										<i class="fa-search"></i>
										<span>搜索</span>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">demo列表</h3>
						<div class="panel-options">
							<a href="/auth/demo/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="55" field="index">编号</th>
									<th field="title" url="http://my.demo/" parm="url">标题</th>
									<th field="menuId">菜单</th>
									<!-- <th field="picPath">图片地址</th>
									<th field="resourcePath">资源地址</th>
									<th field="url">地址</th> -->
									<th field="downloadTimes">下载次数</th>
									<th field="createTime">创建时间</th>
									<th field="updateTime">更新时间</th>
									<th field="op" field-role="0,2"></th>
								</tr>
							</thead>
							<tbody class="middle-align"></tbody>
						</table>
						<div class="pagebar"></div>
					</div>
					
				</div>
			</div>
		
		</div>
		<jsp:include page="../include/chat.jsp"></jsp:include>
	</div>
	
<jsp:include page="../dialog/dialog_delete.jsp"></jsp:include>	
<script>
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(5).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(5).find('ul li').eq(0).addClass('active');
	
	/* $('a[data-toggle="sidebar"]').trigger('click'); */
	
	$('#datatable').datatable({
		url_load : '/auth/demo/getList',
		backFn : function(p) {
			// console.log(p);
		}
	});  
});

	function search() {
		var form = $('#form');
		var title = form.find('input[name="title"]').val();
		var menuId = form.find('select[name="menuId"]').val();
		var parm = {
				pageNum : 1,
				pageSize : 10,
				title : title,
				menuId : menuId
			}
		$.fn.doSearch(parm);
	}
	
	function showDeleteDialog(obj) {
		var _id = $(obj).attr("id");
		var _url = '/auth/demo/doDelete';
		$("#modal-delete .modal-body").html('确定删除?');
		$("input[name='hidden-url']").val(_url);
		$("input[name='hidden-id']").val(_id);
		jQuery("#modal-delete").modal("show", {backdrop: "fade"});
	}
	
	function edit(obj) {
		var url="/auth/demo/edit?id=" + $(obj).attr("id");
		window.open(url);
	}
	
</script>
</body>
</html>