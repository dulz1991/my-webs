<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>技术博客日志</title>
</head>
<body class="page-body chat-open">
	<div class="page-container">
		<jsp:include page="../include/left.jsp"></jsp:include>
		
		<div class="main-content">
		<jsp:include page="../include/header.jsp"></jsp:include>
			
			<div class="row">
				<div class="col-sm-12 panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">搜索框</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" id="form" action="javascript:void(0);">
							<div class="form-group">
								<div class="col-sm-3">
									<input type="text" class="form-control input" name="username" value="" placeholder="输入用户名">
								</div>
								<div class="col-sm-2">
									<button class="btn btn-info btn-icon" onclick="search();">
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
						<h3 class="panel-title">技术博客日志列表</h3>
						<div class="panel-options">
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th field="index">编号</th>
									<th field="username">用户名</th>
									<th field="userId">用户ID</th>
									<th field="remark">备注</th>
									<th field="createTime">更新时间</th>
								</tr>
							</thead>
							<tbody class="middle-align"></tbody>
						</table>
						<div class="pagebar"></div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
<jsp:include page="../dialog/dialog_delete.jsp"></jsp:include>	
<script src="/mine/js/common-util.js"></script>
<script src="/mine/js/jquery.datatable.js"></script>	
<script>
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(2).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(2).find('ul li').eq(2).addClass('active');
	
	$('#datatable').datatable({
		url_load : '/auth/bloglog/getList',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});

	function search() {
		var form = $('#form');
		var username = encodeURI(form.find('input[name="username"]').val());
		var parm = {
				pageNum : 1,
				pageSize : 10,
				username : username
			}
		$.fn.doSearch(parm);
	}
	
</script>
</body>
</html>