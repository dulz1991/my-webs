<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>用户管理</title>
	
</head>
<body class="page-body">
	<jsp:include page="../include/hidden_header.jsp"></jsp:include>
	
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
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
								<label class="col-sm-2 control-label" for="username">用户名</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="username" id="username" placeholder="输入用户名">
								</div>
								<div class="col-sm-2">
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
					<h3 class="panel-title">用户列表</h3>
					<div class="panel-options">
						<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
					</div>
					<!-- <div class="panel-options">
						<a href="#" data-toggle="panel">
							<span class="collapse-icon">&ndash;</span>
							<span class="expand-icon">+</span>
						</a>
						<a href="#" data-toggle="remove">
							&times;
						</a>
					</div> -->
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th field="index">编号</th>
									<th field="id">用户ID</th>
									<th field="username">用户名</th>
									<th field="email">邮箱</th>
									<th field="phone">手机</th>
									<th field="role">权限</th>
									<th field="createTime">创建时间</th>
									<th field="updateTime">更新时间</th>
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
	
<script>
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(1).addClass('active').addClass('opened');

	$('#datatable').datatable({
		url_load : '/auth/user/getList',
		backFn : function(p) {
			// console.log(p);
		}
	});  
});

function search() {
	var form = $('#form');
	var username = form.find('input[name="username"]').val();
	var parm = {
			pageNum : 1,
			pageSize : 10,
			username : username,
		}
	$.fn.doSearch(parm);
}
</script>
</body>
</html>